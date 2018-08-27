/**
  * Copyright (C) 2018 Ioannis Canellos 
  *     
  * 
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  * 
  *         http://www.apache.org/licenses/LICENSE-2.0
  * 
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
**/
package me.snowdrop.cloud.servicecatalog.connector;

import me.snowdrop.servicecatalog.api.client.ServiceCatalogClient;
import me.snowdrop.servicecatalog.api.model.ServiceInstance;
import org.springframework.cloud.service.common.SqlServerServiceInfo;

public class SqlServerServiceInfoCreator extends RelationalDatabaseServiceInfoCreator<SqlServerServiceInfo> {

    protected static final String SCHEME = "sqlserver";
    protected static final String DEFAULT_PORT = "1433";


    public SqlServerServiceInfoCreator() {
        super(ServiceCatalogClientRef.getClient());
    }

    public SqlServerServiceInfoCreator(ServiceCatalogClient client) {
        super(client);
    }

    public String getScheme() {
        return SCHEME;
    }

    public String getDefaultPort() {
        return DEFAULT_PORT;
    }

    @Override
    public SqlServerServiceInfo createServiceInfo(String id, String uri) {
        return new SqlServerServiceInfo(id, uri);
    }


    @Override
    public boolean accept(ServiceInstance instance) {
        String serviceClassName =  instance.getSpec().getClusterServiceClassExternalName();
        return serviceClassName != null && serviceClassName.toLowerCase().contains(SCHEME);
    }
}