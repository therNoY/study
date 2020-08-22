# 7_ElatisSearch term+boolʵ�ֵ�multiword����ԭ��

## ����

* es �ײ�Ὣ match query ת��bool term ��ѯ��match query --> bool + term��

## ����һ
��ͨmatch���ת��Ϊterm+should

```java
{
    "match": { "title": "java elasticsearch"}
}
```

* ʹ�����������match query���ж�ֵ������ʱ��es���ڵײ��Զ������match queryת��Ϊbool���﷨
* bool should��ָ����������ʣ�ͬʱʹ��term query

```java
{
  "bool": {
    "should": [
      { "term": { "title": "java" }},
      { "term": { "title": "elasticsearch"   }}
    ]
  }
}
```

## ���Ӷ�

and match���ת��Ϊterm+must

```java
{
    "match": {
        "title": {
            "query":    "java elasticsearch",
            "operator": "and"
        }
    }
}
```

ת����
```java
{
  "bool": {
    "must": [
      { "term": { "title": "java" }},
      { "term": { "title": "elasticsearch"   }}
    ]
  }
}
```
## ������

minimum_should_match���ת��
```java

{
    "match": {
        "title": {
            "query":                "java elasticsearch hadoop spark",
            "minimum_should_match": "75%"
        }
    }
}
```

ת����

```java
{
  "bool": {
    "should": [
      { "term": { "title": "java" }},
      { "term": { "title": "elasticsearch"   }},
      { "term": { "title": "hadoop" }},
      { "term": { "title": "spark" }}
    ],
    "minimum_should_match": 3 
  }
}
```


