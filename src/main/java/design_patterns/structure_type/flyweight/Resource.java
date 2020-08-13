package design_patterns.structure_type.flyweight;

public class Resource {
    private String hashId;
    private int byteSize;
    private String content;

    public Resource(String content) {
        this.content = content;
        this.hashId = HashUtil.computeHashId(content);   // 文件的hash值
        this.byteSize = content.length();
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public int getByteSize() {
        return byteSize;
    }

    public void setByteSize(int byteSize) {
        this.byteSize = byteSize;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
