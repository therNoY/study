# ����5 Elasticseach ��windows�ϰ�װ������Elasticseach

## �������

���ӣ�https://pan.baidu.com/s/1co-8gEHBwSi5hRBjNMu6Xg ���룺821i

## ����

* 1����װJDK������1.8.0_73���ϰ汾��java -version
* 2�����غͽ�ѹ��Elasticsearch��װ����Ŀ¼�ṹ
* 3������Elasticsearch��bin\elasticsearch.bat��es�����ص�֮һ���ǿ��伴�ã��������С��Ӧ�ã��������٣��������Ǻܸ��ӣ�ֱ�������Ϳ�������
* 4�����ES�Ƿ������ɹ���http://localhost:9200/?pretty

```
name: node����
cluster_name: ��Ⱥ���ƣ�Ĭ�ϵļ�Ⱥ���ƾ���elasticsearch��
version.number: 5.2.0��es�汾��
```

```
{
  "name" : "4onsTYV",
  "cluster_name" : "elasticsearch",
  "cluster_uuid" : "nKZ9VK_vQdSQ1J0Dx9gx1Q",
  "version" : {
    "number" : "5.2.0",
    "build_hash" : "24e05b9",
    "build_date" : "2017-01-24T19:52:35.800Z",
    "build_snapshot" : false,
    "lucene_version" : "6.4.0"
  },
  "tagline" : "You Know, for Search"
}
```

* 5���޸ļ�Ⱥ���ƣ�elasticsearch.yml
* 6�����غͽ�ѹ��Kibana��װ����ʹ������Ŀ������棬ȥ����elasticsearch����Ϊ����ѧϰes֪ʶ���һ����Ҫ�Ľ������
* 7������Kibana��bin\kibana.bat
* 8������Dev Tools���� http://localhost:5601
* 9��GET _cluster/health