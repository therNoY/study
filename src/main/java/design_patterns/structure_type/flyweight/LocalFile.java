package design_patterns.structure_type.flyweight;

public class LocalFile {
    private String filename;
    private String content;

    public LocalFile(String filename, String content) {
        this.filename = filename;
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        PanServer panServer = PanServer.getInstance();
        String fileContent = "这是一个pdf文件《设计模式：从入门到放弃》";
        LocalFile localFile1 = new LocalFile("小明的设计模式.pdf", fileContent);
        String fikeKey1 = panServer.upload("小明", localFile1);

        LocalFile localFile2 = new LocalFile("大明的设计模式.pdf", fileContent);
        String fikeKey2 = panServer.upload("大明", localFile2);

        panServer.download(fikeKey1);
        panServer.download(fikeKey2);
    }


}

