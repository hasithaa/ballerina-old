# Introduction to Ballerina Annotations. 

## Introductions

Ballerina `Annotations` are from of metadata, that attached to a Ballerina construct. By design, Ballerina considers all
annotations as optional, hence A Ballerina Program(Service ?) should be able to run without any annotation. 

Ballerina `Annotations` can be used to annotate following Ballerina constructs.   
 
 * Service
 * Resource
 * Connectors
 * Connections
 * Actions
 * Functions
 * User Defined Types
 * Variables
 

All Ballerina Annotations start with Character `@` (at) and it has following Syntax. 

```
@AnnotationName [ ( PrimitiveValue | "AString"  | key = value [, key = value] )]
```

Here `value` can be one of the followings:
* Primitive value. (i.e. integer, long, float, double, boolean)
* A String. (e.g. "aValue")
* Another Annotation. (This can't be the parent or enclosing annotation)
* An Array of one of the above. (e.g. { "String 1", "String 2" })

E.g:
```
    @AnnotationOne
    
    @AnnotationTwo("Value of two")
    
    @AnnotationThree( keyInt = 1 , keyString = "second value", keyAnnotation = @InnerAnnotation("Inner Annotation Value"))
    
    @AnnotationFour( 
        keyStringArray = { "value 1" , "value 2"} ,
        keyAnnotationArray = {
            @InnerAnnotation( innerKeyInt = 1, innerKeyString = "A String"),
            @InnerAnnotation( innerKeyInt = 1, innerKeyString = "A String")
        }
    )
```

In Ballerina annotations are divided into two categories.

* Documentation Annotations (Doc Annotation)
    - These annotations represent structured meta information about the Ballerina constructs that they annotated.(Similar to
     Java Doc comment.) 
* Config Annotations
    - Config annotations denote additional configuration/behavior instructions for Ballerina runtime.
     (e.g Apply Security, Circuit Breaker, etc) By defining the config annotations for a construct, Ballerina developer 
     can instruct to the Ballerina runtime, to alter default behavior or/and apply Quality of services configuration.

## Swagger 2.0 Support

Ballerina Annotations represent a supper set of Open API Specification 2.0 (AKA Swagger 2.0) format. This allows developers
to Generate a Ballerina service skeleton from a Swagger 2.0 definition or Swagger 2.0 definition from a Ballerina service.
 
## Supported Annotations.

### Service Annotations. 

Followings are the service level annotations.

* API Definition
* API Configuration
* Path
* Consumes
* Produces

#### API Definition

A Doc annotation, which describes Ballerina Annotation. 

```
@APIDefinition( // Alternatives : @Info, @ServiceInfo, @APIInfo, @APIDocumentation
    SwaggerVersion = "2.0", 
    info = @Info( // Swagger Info (Required)
        title = "Sample API title", // Swagger Info.title (Required)
        description =  "Sample Description.", // Swagger Info.Description 
        version = "1.0.0", // Swagger Info.Version (Required)
        termsOfService = "http://example.com/terms", // Swagger Info.TermsOfService
        contact = @Contact( // Swagger Info.contact
            name = "wso2" ,
            url = "http//wso2.com"
            [,anyName = anyValue]* // Swagger element "x-anyName" : "anyValue"
        ), 

        license = @License( // Swagger Info.license
            name = "Apache 2",
            url = "http://www.apache.org/licenses/LICENSE-2.0"
            [,anyName = anyValue]* // Swagger element "x-anyName" : "anyValue"
        ) 

        [,anyName = anyValue]* // Swagger element "x-anyName" : "anyValue"
    ),

    externalDocs = @ExternalDocs( // Swagger external Docs
        value = "wso2 ballerina",
        url = "http://docs.wso2.com/ballerina"
        [,anyName = anyValue]* // Swagger element "x-anyName" : "anyValue"
    ),
    
    tags = { // Swagger tags element.
            @Tag( // Swagger tag element.
                name = "HTTP",
                description = "HTTP Service",
                externalDocs = @ExternalDocs(
                    value = "HTTP Service",
                    url = "http://docs.wso2.com/http"
                    [,anyName = anyValue]* // this will represent swagger element "x-anyName" : "anyValue"
                )
            ),
            @Tag(name = "Private", description = "Private Service")
        }
)
```

_@APIDefinition_

Ballerina Field Name  |  Description | Equivalent Swagger Field
----------------------|--------------|-------------------------
SwaggerVersion | Optional. Specifies the Swagger Specification version to be used. Default value is "2.0". | swagger 
info | Required Field. Provides metadata about the Ballerina API. | info
externalDocs |A list of tags used by the specification with additional metadata.| externalDocs
tags | Optional. A list of tags used by the specification with additional metadata. | tags

_@Info_

Ballerina Field Name  |  Description | Equivalent Swagger Field
----------------------|--------------|-------------------------
title||
description||
version||
termsOfService||
contact||
license||
anyName*||




#### API Configuration


#### Path


#### Consumes


#### Produces


### Resource Annotation.

Following are the resource level annotations.

* Path
* HTTP method
* Resource Config
* Consumes
* Produces
* Method Parameter Definition
* Resource Info
* Responses

#### Path
```
@Path("/resource")
```
#### HTTP method
```
(@GET | @POST | @PUT | @DELETE | @OPTIONS | @HEAD | @PATCH )* 
```
#### Resource Config

```
@ResourceConfig(
    schemes = {http, https}
    authorizations = {
        @Authorization(value="authorizationConfigName" 
                       [, scopes = { "scope1", "scope2" }]
        )
    }
    [,anyName = anyValue]* // this will represent swagger element "x-anyName" : "anyValue"
)
```

|Ballerina Field Name  |  Type | Description | Equivalent Swagger Field|
|---|---|---|---|
|  schemas|array |The transfer protocol for the ballerina resource. Values can be "http", "https", "ws" or "wss".  | schemas  | 
|  authorizations|annotation |A declaration of which authorizations configurations are applied for this resource.  | security  | 
|anyName |any | Extension fields. | x-anyName, Maps to Swagger extensions. Field name begins with `x-` |

#### Consumes
```
@Consumes({ "application/json", "application/xml" })
```
#### Produces
```
@Produces({ "application/json", "application/xml" })
```
#### Method Parameter Definition
```
QueryParam
@Path("/foo")
resourceName(message m, @QueryParam(name = "paramName", description = "A Description", required = true) string name, ...){...}

PathParam
@Path("/foo/{paramName}")
resourceName(message m, @PathParam(name = "paramName", description = "A Description", required = true) string name, ...){...}

FormParam
@Path("/foo/")
resourceName(message m, @FormParam(name = "paramName", description = "A Description", required = true) string name, ...){...}

HeaderParam
@Path("/foo/")
resourceName(message m, @HeaderParam(name = "paramName", description = "A Description", required = true) string name, ...){...}

Body
@Path("/foo/")
resourceName(message m, @Type(name = "TypeName", description = "A Description", required = true) TypeName typeVariableName, ...){...}

```
#### Resource Info
```
@ResourceInfo( 
    tags = { "http", "get", "anotherTag" },
    summary = "A summary about resource",
    description = "a detailed description about resource",
    externalDocs = @ExternalDocs(
                       value = "wso2 ballerina", 
                       url = "http://docs.wso2.com/ballerina"
                   ),
    operationId = "methodName" // This information is redundant in resource name, But we need this in special cases.
)
```
|Ballerina Field Name  |  Type | Description | Swagger Field|
|---|---|---|---|
|  tags|array |A list of tags for resource documentation control  | tags  | 
|  summary|string |A short summary of what the resource does.  | summary  | 
|  externalDocs|annotation |Additional external documentation for this resource.  | externalDocs  | 
|  operationId|string |Unique string used to identify the resource.  | operationId  | 

#### Responses
```
@Responses( 
    values = {
        @Response( 
            code = 200,
            [description = "Human-readable message to accompany the response.",
            response = TypeName|VaribaleType|ArrayType|XML<Schema>|JSON<Schema>
            headers = {
                @Header(
                    name = "HeaderName",
                    description = "description",
                    type = TypeName|VaribaleType|ArrayType|XML<Schema>|JSON<Schema>
                    ),
                @Header(...)
            },
            examples = {
                @Example(
                    type = "mime-type",
                    value = xml|json|string
                ),
                @Example(
                    type = "application/json",
                    value = `{ "key1": "value1", "key2", "value2"}` 
                )
            }] 
            | [reference = "String to reference"]
        ),
        @Response( 
            code = "default", 
            description = "unexpected error",
            ...)
    }
)

```
|Ballerina Field Name  |  Type | Description | Swagger Field|
|---|---|---|---|
|  Responses|annotation |The list of possible responses as they are returned from executing this operation  | Responses  | 

### Connector Annotations.

### Connection Annotations.
 
### Action Annotations. 

### Function Annotations. 

### Type Annotations.

### Variable Annotations. 

