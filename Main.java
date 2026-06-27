import java.util.Optional;

public class Main {
  public static void main(String[] args) {
    // ユーザー情報を取得するためのリポジトリを生成する
    UserRepository userRepository = new UserRepository();

    // Optional#get() で値を取り出すパターン（値が無い場合は例外が発生する）
    try {
      // ID:1 のユーザーを検索する（存在するため値を取得できる）
      Optional<User> userOptional1 = userRepository.findById(1);
      // Optional から中身の User を取り出す
      User user1 = userOptional1.get();
      System.out.println("ユーザー情報が見つかりました: " + user1);

      // ID:2 のユーザーを検索する（存在しない場合は空の Optional が返る）
      Optional<User> userOptional2 = userRepository.findById(2);
      // 値が空の場合 get() は NoSuchElementException をスローする
      User user2 = userOptional2.get();
      System.out.println("ユーザー情報が見つかりました: " + user2);
    } catch (RuntimeException e) {
      e.printStackTrace();
    }

    // Optional#orElseThrow() で値が無いときに任意の例外を投げるパターン
    try {
      // ID:1 のユーザーを検索する（存在する）
      Optional<User> userOptional3 = userRepository.findById(1);
      // 値があればそのまま取得し、無ければ指定した例外をスローする
      User user3 = userOptional3.orElseThrow(() -> new RuntimeException("該当するユーザー情報が存在しません"));
      System.out.println("ユーザー情報が見つかりました: " + user3);

      // ID:4 のユーザーを検索する（存在しない）
      Optional<User> userOptional4 = userRepository.findById(4);
      // 値が無いため orElseThrow で指定した例外がスローされる
      User user4 = userOptional4.orElseThrow(() -> new RuntimeException("該当するユーザー情報が存在しません"));
      System.out.println("ユーザー情報が見つかりました: " + user4);
    } catch (RuntimeException e) {
      e.printStackTrace();
    }

    // Optional#map() で値を変換してから取り出すパターン
    try {
      // ID:1 のユーザーを検索する（存在する）
      Optional<User> userOptional5 = userRepository.findById(1);
      // User が存在すれば name を取り出して Optional<String> に変換する
      Optional<String> nameOptional5 = userOptional5.map(user -> user.name());
      // 変換後の値を取得し、無ければ例外をスローする
      String name5 = nameOptional5.orElseThrow(() -> new RuntimeException("該当するユーザー情報が存在しません"));
      System.out.println("ユーザー情報が見つかりました" + name5);

      // ID:6 のユーザーを検索する（存在しない）
      Optional<User> userOptional6 = userRepository.findById(6);
      // 元の Optional が空のため map の結果も空になる
      Optional<String> nameOptional6 = userOptional6.map(user -> user.name());
      // 値が無いため orElseThrow で指定した例外がスローされる
      String name6 = nameOptional6.orElseThrow(() -> new RuntimeException("該当するユーザー情報が存在しません"));
      System.out.println("ユーザー情報が見つかりました" + name6);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
