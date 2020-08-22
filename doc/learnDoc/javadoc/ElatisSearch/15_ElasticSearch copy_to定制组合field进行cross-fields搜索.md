# 15_ElasticSearch copy_to�������field����cross-fields����

## ������

��most_fields���ԣ�ȥʵ��cross-fields���� ���⣺

* ֻ���ҵ������ܶ��fieldƥ���doc��������ĳ��field��ȫƥ���doc
* most_fields��û�취��minimum_should_matchȥ����β���ݣ�����ƥ����ر��ٵĽ��
* TF/IDF�㷨������Peter Smith��Smith Williams������Peter Smith��ʱ������first_name�к�����Smith�ģ�����query������document�е�Ƶ�ʺܵͣ��õ��ķ����ܸߣ�����Smith Williams����������Peter Smithǰ��


## ��copy_to ȥʵ��cross-fields����

* ��copy_to�������field��ϳ�һ��field
* ������ʵ�ͳ����ж��field���ж��field�Ժ󣬾ͺ����Σ�����ֻҪ��취��һ����ʶ���ڶ��field��������ϲ���һ��field���ɡ�
* ����˵��һ��������������first_name��last_name�����ںϲ���һ��full_name������ok����

## ����

* ���� new_author_full_name �ֶ�

```java

PUT /forum/_mapping/article
{
  "properties": {
      "new_author_first_name": {
          "type":     "string",
          "copy_to":  "new_author_full_name" 
      },
      "new_author_last_name": {
          "type":     "string",
          "copy_to":  "new_author_full_name" 
      },
      "new_author_full_name": {
          "type":     "string"
      }
  }
}
```

�������copy_to�﷨֮�󣬾Ϳ��Խ�����ֶε�ֵ������һ���ֶ��У���������������

```java
POST /forum/article/_bulk
{ "update": { "_id": "1"} }
{ "doc" : {"new_author_first_name" : "Peter", "new_author_last_name" : "Smith"} }		--> Peter Smith
{ "update": { "_id": "2"} }	
{ "doc" : {"new_author_first_name" : "Smith", "new_author_last_name" : "Williams"} }		--> Smith Williams
{ "update": { "_id": "3"} }
{ "doc" : {"new_author_first_name" : "Jack", "new_author_last_name" : "Ma"} }			--> Jack Ma
{ "update": { "_id": "4"} }
{ "doc" : {"new_author_first_name" : "Robbin", "new_author_last_name" : "Li"} }			--> Robbin Li
{ "update": { "_id": "5"} }
{ "doc" : {"new_author_first_name" : "Tonny", "new_author_last_name" : "Peter Smith"} }		--> Tonny Peter Smith
```

��ѯ��

```java
GET /forum/article/_search
{
  "query": {
    "match": {
      "new_author_full_name":       "Peter Smith"
    }
  }
}
```

### �����most_fields���� ���ڵ�����

* ֻ���ҵ������ܶ��fieldƥ���doc��������ĳ��field��ȫƥ���doc --> �������ƥ���document�����ȷ���
* most_fields��û�취��minimum_should_matchȥ����β���ݣ�����ƥ����ر��ٵĽ�� --> ���������ʹ��minimum_should_matchȥ����β����
* TF/IDF�㷨������Peter Smith��Smith Williams������Peter Smith��ʱ������first_name�к�����Smith�ģ�����query������document�е�Ƶ�ʺܵͣ��õ��ķ����ܸߣ�����Smith Williams����������Peter Smithǰ�� --> �����Smith��Peter��һ��field�ˣ�����������document�г��ֵĴ����Ǿ��ȵģ������м��˵�ƫ��



