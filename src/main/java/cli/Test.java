package cli;

public class Test {


    public static void main(String[] args) {
        String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex("root");
        System.out.println(sha256hex);
    }

}
