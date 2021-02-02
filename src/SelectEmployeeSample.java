import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectEmployeeSample {
  public static void main(String[] args) {
    Connection conn = null;
    try {

      // データベースへ接続
      conn = DriverManager.getConnection(
          "jdbc:mysql://localhost/example?useSSL=false",
        "root",
        "");
      // SELECT文を準備
      String sql = "SELECT ID,NAME,AGE FROM EMPLOYEE";
      PreparedStatement pStmt = conn.prepareStatement(sql);

      // SELECTを実行し、結果表（ResultSet）を取得
      ResultSet rs = pStmt.executeQuery();

      // 結果表に格納されたレコードの内容を表示
      while (rs.next()) {

        String id = rs.getString("ID");
        String name = rs.getString("NAME");
        int age = rs.getInt("AGE");

        // 取得したデータを出力
        System.out.println("ID:" + id);
        System.out.println("名前:" + name);
        System.out.println("年齢:" + age + "\n");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // データベース切断
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
