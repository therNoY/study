# 12_ElastisSearch multi_match�﷨ʵ��dis_max+tie_breaker

## ����:

> dis_max
* score�����Ӳ�ѯscore�����ֵ

> tie_breaker
* ����ͨ��tie_breaker����������field�ĵ÷�

> minimum_should_match����Ҫ������:
* 1��ȥ��β��long tail
* 2����β������������5���ؼ��ʣ����Ǻܶ�����ֻƥ��1���ؼ��ʵģ���ʵ������Ҫ�Ľ�������Զ����Щ������ǳ�β
* 3��minimum_should_match��������������ľ�׼�ȣ�ֻ��ƥ��һ�������Ĺؼ��ʵ����ݣ����ܷ���

## ���ӣ�

```java
GET /forum/article/_search
{
  "query": {
    "multi_match": {
        "query":                "java solution",
        "type":                 "best_fields", 
        "fields":               [ "title^2", "content" ],
        "tie_breaker":          0.3,
        "minimum_should_match": "50%" 
    }
  } 
}
```

```java

GET /forum/article/_search
{
  "query": {
    "dis_max": {
      "queries":  [
        {
          "match": {
            "title": {
              "query": "java beginner",
              "minimum_should_match": "50%",
			  "boost": 2
            }
          }
        },
        {
          "match": {
            "body": {
              "query": "java beginner",
              "minimum_should_match": "30%"
            }
          }
        }
      ],
      "tie_breaker": 0.3
    }
  } 
}
```




