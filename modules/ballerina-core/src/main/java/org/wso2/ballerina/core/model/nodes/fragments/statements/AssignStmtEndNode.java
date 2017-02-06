/*
*   Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.ballerina.core.model.nodes.fragments.statements;

import org.wso2.ballerina.core.model.LinkedNodeExecutor;
import org.wso2.ballerina.core.model.nodes.AbstractLinkedNode;
import org.wso2.ballerina.core.model.statements.AssignStmt;

/**
 * Represents end of an Assign Statement, where actual assignment happens.
 */
public class AssignStmtEndNode extends AbstractLinkedNode {

    private AssignStmt statement;

    public AssignStmtEndNode(AssignStmt stmt) {
        this.statement = stmt;
        this.parent = stmt;
    }

    public AssignStmt getStatement() {
        return statement;
    }

    @Override
    public void executeLNode(LinkedNodeExecutor executor) {
        executor.visit(this);
    }
}
