# 9_ElastisSearch ��shard������relevance score��׼ȷ


## ͼ��

������һ��index�ж��shard�Ļ���������������᲻׼ȷ

![image](https://github.com/csy512889371/learnDoc/blob/master/image/2018/es/1.png)

## ��ν��������

### 1�����������£��������󣬾�����ʵ�־��ȷ���

* �������ܴ�Ļ�����ʵһ������£��ڸ���ѧ�ı����£�es�����ڶ��shard�о���·�����ݵģ�·�ɵ�ʱ�����_id�����ؾ���
* ����˵��10��document��title������java��һ����5��shard����ô�ڸ���ѧ�ı����£�������ؾ���Ļ�����ʵÿ��shard��Ӧ����2��doc��title����java
* ���˵���ݷֲ����ȵĻ�����ʵ��û�иղ�˵���Ǹ�������

### 2�����Ի�����

* ��������primary shard����Ϊ1����number_of_shards=1��index settings
* ���˵ֻ��һ��shard����ô��Ȼ�����е�document�������shard���棬��û�����������

### 3�����Ի�����
* ��������search_type=dfs_query_then_fetch�������Ὣlocal IDFȡ��������global IDF
* ����һ��doc����ضȷ�����ʱ�򣬾ͻὫ����shard�Ե�local IDF����һ�£���ȡ�������ڱ��ؽ���global IDF�����ļ��㣬�Ὣ����shard��doc��Ϊ�����������м��㣬Ҳ��ȷ��׼ȷ�ԡ�
* ����production���������£����Ƽ������������Ϊ���ܺܲ

