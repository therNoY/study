# 10_ElastisSearch dis_maxʵ��best fields���Խ��ж��ֶ�����

## ����

> best fields���� ����

* ���ڶ�� field ��ѯ�� title(����) content ����. 
* ����title��content�а���java��solution������
* ���������title�а�����java��solution ������ content �а��� java��solution ������doc ��������ǰ�档
* best fields���ԣ�����˵���������Ľ����Ӧ����**ĳһ��field**��ƥ�䵽�˾����ܶ�Ĺؼ��ʣ�������ǰ�棻�����Ǿ����ܶ��fieldƥ�䵽�������Ĺؼ��ʣ�������ǰ�档


## ����

### 1��title �ֶ�

```java
POST /forum/article/_bulk
{ "update": { "_id": "1"} }
{ "doc" : {"title" : "this is java and elasticsearch blog"} }
{ "update": { "_id": "2"} }
{ "doc" : {"title" : "this is java blog"} }
{ "update": { "_id": "3"} }
{ "doc" : {"title" : "this is elasticsearch blog"} }
{ "update": { "_id": "4"} }
{ "doc" : {"title" : "this is java, elasticsearch, hadoop blog"} }
{ "update": { "_id": "5"} }
{ "doc" : {"title" : "this is spark blog"} }
```

### 2��Ϊ������������content�ֶ�

```java

POST /forum/article/_bulk
{ "update": { "_id": "1"} }
{ "doc" : {"content" : "i like to write best elasticsearch article"} }
{ "update": { "_id": "2"} }
{ "doc" : {"content" : "i think java is the best programming language"} }
{ "update": { "_id": "3"} }
{ "doc" : {"content" : "i am only an elasticsearch beginner"} }
{ "update": { "_id": "4"} }
{ "doc" : {"content" : "elasticsearch and hadoop are all very good solution, i am a beginner"} }
{ "update": { "_id": "5"} }
{ "doc" : {"content" : "spark is best big data solution based on scala ,an programming language similar to java"} }
```

### 3������title��content�а���java��solution������

�����������multi-field���������ֶ�����

```java
GET /forum/article/_search
{
    "query": {
        "bool": {
            "should": [
                { "match": { "title": "java solution" }},
                { "match": { "content":  "java solution" }}
            ]
        }
    }
}

```

### 4���������

* ��������doc5�������doc2, doc4������ǰ�� (doc 5 �� content�ֶ� �б����� java �� solution)
* ����ÿ��document��relevance score��ÿ��query�ķ���������matched query������������query����

> ��һ��doc4�ķ���

```xml
{ "match": { "title": "java solution" }}�����doc4������һ��������
{ "match": { "content":  "java solution" }}�����doc4��Ҳ����һ��������

��������������������������˵��1.1 + 1.2 = 2.3
matched query���� = 2
��query���� = 2

2.3 * 2 / 2 = 2.3
```

> ��һ��doc5�ķ���

```xml
{ "match": { "title": "java solution" }}�����doc5����û�з�����
{ "match": { "content":  "java solution" }}�����doc5������һ��������

����˵��ֻ��һ��query���з����ģ�����2.3
matched query���� = 1
��query���� = 2

2.3 * 1 / 2 = 1.15

doc5�ķ��� = 1.15 < doc4�ķ��� = 2.3
```

### 5��best fields���ԣ�dis_max

* best fields���ԣ�����˵���������Ľ����Ӧ����ĳһ��field��ƥ�䵽�˾����ܶ�Ĺؼ��ʣ�������ǰ�棻�����Ǿ����ܶ��fieldƥ�䵽�������Ĺؼ��ʣ�������ǰ��
* dis_max�﷨��ֱ��ȡ���query�У�������ߵ���һ��query�ķ�������

```xml
{ "match": { "title": "java solution" }}�����doc4������һ�������ģ�1.1
{ "match": { "content":  "java solution" }}�����doc4��Ҳ����һ�������ģ�1.2
ȡ��������1.2
```

```xml
{ "match": { "title": "java solution" }}�����doc5����û�з�����
{ "match": { "content":  "java solution" }}�����doc5������һ�������ģ�2.3
ȡ��������2.3
```

Ȼ��doc4�ķ��� = 1.2 < doc5�ķ��� = 2.3������doc5�Ϳ������ڸ�ǰ��ĵط����������ǵ���Ҫ

```xml
GET /forum/article/_search
{
    "query": {
        "dis_max": {
            "queries": [
                { "match": { "title": "java solution" }},
                { "match": { "content":  "java solution" }}
            ]
        }
    }
}

```


