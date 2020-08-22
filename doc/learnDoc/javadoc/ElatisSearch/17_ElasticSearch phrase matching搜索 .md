# 17_ElasticSearch phrase matching���� 

## ����

* 0����������java spark ����һ��doc
* 1��java spark���Ϳ���һ���м䲻�ܲ����κ������ַ�����Ҫ������������doc
* 2��java spark������Ҫ��java��spark�������ʿ���Խ����doc�ķ���Խ�ߣ�����Խ��ǰ

����ƥ��

* phrase match ����ƥ��
* proximity match ����ƥ��

## ����

��������

```java
java is my favourite programming language, and I also think spark is a very good big data system.
java spark are very related, because scala is spark's programming language and scala is also based on jvm like java.
```


ʹ��match query������java spark���޷�ʵ�����������磺

```java

{
	"match": {
		"content": "java spark"
	}
}
```

��������java��docҲ�����ˣ�����������Ҫ�Ľ��

POST /forum/article/5/_update
{
  "doc": {
    "content": "spark is best big data solution based on scala ,an programming language similar to java spark"
  }
}


### match query �����⣺

* match query��ֻ������������java��spark��document�����ǲ�֪��java��spark�ǲ�����ĺܽ�
* ����java�����spark�������java��spark��doc�����ᱻ���ػ�����
* ������ʵ����֪���ĸ�doc��java��spark����ıȽϽ���
* ������Ǿ���ϣ������java spark���м䲻�ܲ����κ��������ַ��������ʱ��matchȥ��ȫ�ļ��������޷�ʵ�֡�
* �������Ҫ������java��spark��ĺܽ���document���ȷ��أ�Ҫ����һ�����ߵ�relevance score������漰����proximity match������ƥ��

### ����ƥ��



���˵��Ҫʵ����������

* 1��java spark���Ϳ���һ���м䲻�ܲ����κ������ַ�����Ҫ������������doc
* 2��java spark������Ҫ��java��spark�������ʿ���Խ����doc�ķ���Խ�ߣ�����Խ��ǰ

��proximity match������ƥ�� ʵ����������

* phrase match�����ǽ���������java��spark����һ�����Щdoc�������и�doc����java use'd spark�����С������Ǳ���java spark are very good friends���ǿ������������ġ�
* phrase match������Ҫȥ�����term��Ϊһ�����һ��ȥ������ֻ�а�����������doc�Ż���Ϊ������ء�������match��java spark��java��docҲ�᷵�أ�spark��docҲ�᷵�ء�


match_phrase�﷨


```java
GET /forum/article/_search
{
    "query": {
        "match_phrase": {
            "content": "java spark"
        }
    }
}
```


�ɹ��ˣ�ֻ�а���java spark��������doc�ŷ����ˣ�ֻ����java��doc���᷵��

## match_phrase�Ļ���ԭ��

```java
GET _analyze
{
  "text": "hello world, java spark",
  "analyzer": "standard"
}
```

term position

```java
hello world, java spark		doc1
hi, spark java				doc2
```

�������µ�������������λ����Ϣ.�����е�position��match_phrase

```java
hello 		doc1(0)		
wolrd		doc1(1)
java		doc1(2) doc2(2)
spark		doc1(3) doc2(1)
```

* 1��java spark --> match phrase
* 2��java spark --> java��spark
* 3��java --> doc1(2) doc2(2)
* 4��spark --> doc1(3) doc2(1)

### ���� doc1

* 1.Ҫ�ҵ�ÿ��term���ڵ�һ�����е���Щdoc������Ҫ��һ��doc���������ÿ��term�������ó�����������
* 2��doc1 --> java��spark --> spark positionǡ�ɱ�java��1 --> java��position��2��spark��position��3��ǡ����������
* 3��doc1��������

### ���� doc2

* 1��doc2 --> java��spark 
* 2��java position��2��spark position��1��spark position��java positionС1�������Ǵ�1 
* 3������position�Ͳ����㣬��ôdoc2��ƥ��


