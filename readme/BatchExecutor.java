public class BatchExecutor extends BaseExecutor {
    // 记录预编译的Statement
    private final List<Statement> statementList = new ArrayList();
    // 记录执行的结果
    private final List<BatchResult> batchResultList = new ArrayList();
    
    public int doUpdate(MappedStatement ms, Object parameterObject) throws SQLException {
        Configuration configuration = ms.getConfiguration();
        StatementHandler handler = configuration.newStatementHandler(this, ms, parameterObject, RowBounds.DEFAULT, (ResultHandler)null, (BoundSql)null);
        BoundSql boundSql = handler.getBoundSql();
        String sql = boundSql.getSql();
        Statement stmt;
        if (sql.equals(this.currentSql) && ms.equals(this.currentStatement)) { // 只执行一条
            int last = this.statementList.size() - 1;
            stmt = (Statement)this.statementList.get(last);
            this.applyTransactionTimeout(stmt);
            handler.parameterize(stmt);
            BatchResult batchResult = (BatchResult)this.batchResultList.get(last);
            batchResult.addParameterObject(parameterObject);
        } else {  // 执行多条，预编译Statement就一次，替换?占位符参数是多次
            Connection connection = this.getConnection(ms.getStatementLog());
            stmt = handler.prepare(connection, this.transaction.getTimeout());
            handler.parameterize(stmt);
            this.currentSql = sql;
            this.currentStatement = ms;
            // 添加替换参数后的的Statement 到statementList
            this.statementList.add(stmt);
            this.batchResultList.add(new BatchResult(ms, sql, parameterObject));
        }

        handler.batch(stmt);
        return -2147482646;
    }


    public List<BatchResult> doFlushStatements(boolean isRollback) throws SQLException {
        boolean var17 = false;

        Statement stmt;
        ArrayList var21;
        Iterator var22;
        label179: {
            List var3;
            try {
                var17 = true;
                ArrayList results = new ArrayList();
                if (!isRollback) {
                    int i = 0;
                        
                    // 真正开始执行sql
                    for(int n = this.statementList.size(); i < n; ++i) {
                        stmt = (Statement)this.statementList.get(i);
                        this.applyTransactionTimeout(stmt);
                        BatchResult batchResult = (BatchResult)this.batchResultList.get(i);
    
                        try {
                            // 一条一条开始执行
                            batchResult.setUpdateCounts(stmt.executeBatch());
                            MappedStatement ms = batchResult.getMappedStatement();
                            List<Object> parameterObjects = batchResult.getParameterObjects();
                            KeyGenerator keyGenerator = ms.getKeyGenerator();
                            if (Jdbc3KeyGenerator.class.equals(keyGenerator.getClass())) {
                                Jdbc3KeyGenerator jdbc3KeyGenerator = (Jdbc3KeyGenerator)keyGenerator;
                                jdbc3KeyGenerator.processBatch(ms, stmt, parameterObjects);
                            } else if (!NoKeyGenerator.class.equals(keyGenerator.getClass())) {
                                Iterator var10 = parameterObjects.iterator();
    
                                while(var10.hasNext()) {
                                    Object parameter = var10.next();
                                    keyGenerator.processAfter(this, ms, stmt, parameter);
                                }
                            }
    
                            this.closeStatement(stmt);
                        } catch (BatchUpdateException var18) {
                            StringBuilder message = new StringBuilder();
                            message.append(batchResult.getMappedStatement().getId()).append(" (batch index #").append(i + 1).append(")").append(" failed.");
                            if (i > 0) {
                                message.append(" ").append(i).append(" prior sub executor(s) completed successfully, but will be rolled back.");
                            }
    
                            throw new BatchExecutorException(message.toString(), var18, results, batchResult);
                        }
    
                        results.add(batchResult);
                    }
    
                    var21 = results;
                    var17 = false;
                    break label179;
                }
    
                var3 = Collections.emptyList();
                var17 = false;
            } finally {
                if (var17) {
                    Iterator var13 = this.statementList.iterator();
    
                    while(var13.hasNext()) {
                        Statement stmt = (Statement)var13.next();
                        this.closeStatement(stmt);
                    }
    
                    this.currentSql = null;
                    this.statementList.clear();
                    this.batchResultList.clear();
                }
            }
    
            var22 = this.statementList.iterator();
    
            while(var22.hasNext()) {
                stmt = (Statement)var22.next();
                this.closeStatement(stmt);
            }
    
            this.currentSql = null;
            this.statementList.clear();
            this.batchResultList.clear();
            return var3;
        }
    
        var22 = this.statementList.iterator();
    
        while(var22.hasNext()) {
            stmt = (Statement)var22.next();
            this.closeStatement(stmt);
        }
    
        this.currentSql = null;
        this.statementList.clear();
        this.batchResultList.clear();
        return var21;
    }
}