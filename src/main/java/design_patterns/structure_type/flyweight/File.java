package design_patterns.structure_type.flyweight;

public  class File {
    protected String owner;
    protected String filename;
    protected Resource resource;

    public File(String owner, String filename) {
        this.owner = owner;
        this.filename = filename;
    }

    public File(String filePath) {
    }

    public String fileMeta() {// 文件存储到文件系统中需要的key
        if (this.owner == null || filename == null || resource == null) {
            return "未知文件";
        }
        return owner + "-" + filename + resource.getHashId();
    }


    public String display() {
        return fileMeta() + ", 资源内容：" + getResource().toString();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
