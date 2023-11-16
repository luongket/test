package model.testmysql;

public class words {
    private int id;
    private String word;
    private String meaning;
    private String note;

    public words() {
    }

    public words(int id, String word, String meaning, String note) {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
