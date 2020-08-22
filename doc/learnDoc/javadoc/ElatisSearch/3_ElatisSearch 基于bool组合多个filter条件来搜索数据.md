# 3_ElatisSearch ����bool��϶��filter��������������

## ����һ

1��������������Ϊ2017-01-01����������IDΪXHDK-A-1293-#fJ3�����ӣ�ͬʱҪ�����ӵķ������ھ��Բ�Ϊ2017-01-02

������������sql:
```sql
select *
from forum.article
where (post_date='2017-01-01' or article_id='XHDK-A-1293-#fJ3')
and post_date!='2017-01-02'
```

```java
GET /forum/article/_search
{
  "query": {
    "constant_score": {
      "filter": {
        "bool": {
          "should": [
            {"term": { "postDate": "2017-01-01" }},
            {"term": {"articleID": "XHDK-A-1293-#fJ3"}}
          ],
          "must_not": {
            "term": {
              "postDate": "2017-01-02"
            }
          }
        }
      }
    }
  }
}
```

must��should��must_not��filter������ƥ�䣬����ƥ����������һ�����ɣ����벻ƥ��

## ���Ӷ�
2����������IDΪXHDK-A-1293-#fJ3������������IDΪJODL-X-1937-#pV7���ҷ�������Ϊ2017-01-01������

������������sql:

```sql
select *
from forum.article
where article_id='XHDK-A-1293-#fJ3'
or (article_id='JODL-X-1937-#pV7' and post_date='2017-01-01')
```

```java
GET /forum/article/_search 
{
  "query": {
    "constant_score": {
      "filter": {
        "bool": {
          "should": [
            {
              "term": {
                "articleID": "XHDK-A-1293-#fJ3"
              }
            },
            {
              "bool": {
                "must": [
                  {
                    "term":{
                      "articleID": "JODL-X-1937-#pV7"
                    }
                  },
                  {
                    "term": {
                      "postDate": "2017-01-01"
                    }
                  }
                ]
              }
            }
          ]
        }
      }
    }
  }
}
```


## ֪ʶ��
* 1��bool��must��must_not��should����϶����������
* 2��bool����Ƕ��
* 3���൱��SQL�еĶ��and����������������﷨ѧ�����Ժ󣬻�������ʵ�ֲ��ֳ��õ�sql�﷨��Ӧ�Ĺ���


