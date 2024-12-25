## データベース
- データベースをセットアップする場合は、以下のSQLを実行してください。
``` sql
CREATE DATABASE kona_coffee_db
CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

USE kona_coffee_db;

CREATE TABLE coffee (
   id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(50) UNIQUE NOT NULL,
   maker VARCHAR(20),
   point INT,
   url VARCHAR(255)
);

CREATE TABLE admins (
   id INT PRIMARY KEY AUTO_INCREMENT,
   login_id VARCHAR(30) UNIQUE NOT NULL,
   login_pass CHAR(60) NOT NULL
);

INSERT INTO coffee VALUES
(1, "ブルーマウンテン", "小川珈琲", 80, "https://oc-shop.co.jp/products/549?srsltid=AfmBOorNOBhxJ9xs5DmpXCYM1Q2Cg-aCWb3tmCXg1BPcLa8xs1YKAmmQ"),
(2, "Brazil Fazenda Bau", "タリーズコーヒージャパン", 75, "https://www.tullys.co.jp/menu/beans/varietal/brazil_fazenda_bau.html"),
(3, "プレミアムマイルドブレンド", "成城石井", 85, "https://seijoishii.com/products/4953762413860?srsltid=AfmBOorUsBei7vNvdnp41QRZCtxxhVvG9jJ_2bm9ifzuOrIJlLhVFrWM");

-- パスワードは「coffee」
INSERT INTO admins VALUES
(1, "arabica", "$2a$10$s.pp9TzBhIkLyLVMtkTvZeD.5WaN.QsKOPtEkAGkjTGoAOWL7gGie"),
(2, "robusta", "$2a$10$330.Ijb4G8j00S/FAXv4C.7lRdm8ENhrfFTVO1CQrrCyJP.pCRahC");
```

