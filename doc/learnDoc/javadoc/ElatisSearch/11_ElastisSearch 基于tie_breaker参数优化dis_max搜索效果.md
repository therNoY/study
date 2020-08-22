# 11_ElastisSearch ����tie_breaker�����Ż�dis_max����Ч��

## ����

* ��Щ��������̫�ø��ֵģ���Ϊ������������Ҫ����ȥ���첻ͬ���ı���Ȼ��ȥ����һЩ����������ȥ�ﵽ��Ҫ��һ��Ч��
* dis_max��ֻ��ȡ������ߵ��Ǹ�query�ķ������ѡ�

> ������ʵ�ʳ����г��ֵ�һ������������ģ�

* 1��ĳ�����ӣ�doc1��title�а���java��1����content������java beginner�κ�һ���ؼ��� 
* 2��ĳ�����ӣ�doc2��content�а���beginner��1����title�в������κ�һ���ؼ���
* 3��ĳ�����ӣ�doc3��title�а���java��1����content�а���beginner��1��
* 4������3��doc�����score����1�������ճ���������һ������Ҫ�Ľ��
* 5���������������ܳ����Ľ���ǣ�doc1��doc2����doc3��ǰ�棬����������������doc3������ǰ��

ԭ��

* dis_maxֻȡĳһ��query���ķ�������ȫ����������query�ķ���


## ����

* ����title��content�а���java beginner������

```java
GET /forum/article/_search
{
    "query": {
        "dis_max": {
            "queries": [
                { "match": { "title": "java beginner" }},
                { "match": { "body":  "java beginner" }}
            ]
        }
    }
}
```

## tie_breaker �Ż� dis_max
ʹ��tie_breaker������query�ķ���Ҳ���ǽ�ȥ

* tie_breaker���������壬����˵��������query�ķ���������tie_breaker��Ȼ���ۺ�����߷������Ǹ�query�ķ������ۺ���һ����м���
* ����ȡ��߷����⣬���ῼ��������query�ķ���
* tie_breaker��ֵ����0~1֮�䣬�Ǹ�С������ok

```java
GET /forum/article/_search
{
    "query": {
        "dis_max": {
            "queries": [
                { "match": { "title": "java beginner" }},
                { "match": { "body":  "java beginner" }}
            ],
            "tie_breaker": 0.3
        }
    }
}
```



