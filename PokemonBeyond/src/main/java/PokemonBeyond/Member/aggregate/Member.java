package PokemonBeyond.Member.aggregate;

import java.io.Serializable;
import java.util.Objects;

public class Member implements Serializable {
    private String id;
    private String pwd;
    private String name;
    private int age;
    private String nickName;

    public Member() {
    }

    public Member(String nickName) {
        this.nickName = nickName;
    }

    public Member(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public Member(String id, String pwd, String name, int age, String nickName) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.age = age;
        this.nickName = nickName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(nickName, member.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickName);
    }
}
