package firebaseuidemo.eddecanini.firebaseuidemo;

public class Person {

    private String mName;
    private String mDescription;

    public Person() {

    }

    public Person(String name, String description) {
        mName = name;
        mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

}
