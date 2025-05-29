-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.32-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for webtreeshopdata
CREATE DATABASE IF NOT EXISTS `webtreeshopdata` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `webtreeshopdata`;

-- Dumping structure for table webtreeshopdata.account
CREATE TABLE IF NOT EXISTS `account` (
  `accid` binary(16) NOT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `provider` enum('GOOGLE','LOCAL') DEFAULT NULL,
  `role` enum('MANAGER','USER') DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`accid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table webtreeshopdata.account: ~3 rows (approximately)
DELETE FROM `account`;
INSERT INTO `account` (`accid`, `adress`, `avatar`, `date_of_birth`, `email`, `name`, `password_hash`, `phone_number`, `provider`, `role`, `status`) VALUES
	(_binary 0x06b2819412374aed8ea00c61f3a0e25a, '96 định công phương liệt hà nội', 'https://lh3.googleusercontent.com/a/ACg8ocIqo5hfk3Y089i_vPpQH4PRQkvhJHfZx-GYHNAtEWVyvhzuJbU5=s96-c', '2004-10-02', 'hoangducmanh2004789@gmail.com', '20 Hoàng Đức Mạnh', NULL, '0916102968', 'GOOGLE', 'USER', NULL),
	(_binary 0x938b6f6e70bd4089844c74515142d54b, 'Chicago ', NULL, '2004-10-02', 'hoangducmanh999@gmail.com', 'Hoàng Đức Mạnh', '$2a$10$xyUZfd3Kr/6oFJmsa.v84ORKKkUQIBJ05nhGUqOsap0w4qqGURure', '0912184872', 'LOCAL', 'USER', NULL),
	(_binary 0x98d001828c59446c88f4ccbb85304b2f, NULL, 'https://lh3.googleusercontent.com/a/ACg8ocIVrtsXC6Aisy43ewlJTaE2hZnutM2Lrx_WUvrhIVu_7Xq8Ew=s96-c', NULL, 'trananhpham512@gmail.com', 'Trần Anh Phạm', NULL, NULL, 'GOOGLE', 'USER', NULL);

-- Dumping structure for table webtreeshopdata.cart
CREATE TABLE IF NOT EXISTS `cart` (
  `cart_item_id` binary(16) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `account_id` binary(16) DEFAULT NULL,
  `product_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`cart_item_id`),
  KEY `FK4pljlvncf45mr98etwpubxvbt` (`account_id`),
  KEY `FKpu4bcbluhsxagirmbdn7dilm5` (`product_id`),
  CONSTRAINT `FK4pljlvncf45mr98etwpubxvbt` FOREIGN KEY (`account_id`) REFERENCES `account` (`accid`),
  CONSTRAINT `FKpu4bcbluhsxagirmbdn7dilm5` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table webtreeshopdata.cart: ~8 rows (approximately)
DELETE FROM `cart`;
INSERT INTO `cart` (`cart_item_id`, `price`, `quantity`, `account_id`, `product_id`) VALUES
	(_binary 0x72afe34ee6244627ac46788b06476d7e, 43.50, 3, _binary 0x98d001828c59446c88f4ccbb85304b2f, _binary 0x100f222e39e911f084c7005056c00001),
	(_binary 0x83f78f4b60a547dd9e8e9c5701a3471b, 10.85, 1, _binary 0x06b2819412374aed8ea00c61f3a0e25a, _binary 0x100f188639e911f084c7005056c00001),
	(_binary 0x8aab7f5f9daa4987a54ae208dba5f89a, 29.00, 2, _binary 0x06b2819412374aed8ea00c61f3a0e25a, _binary 0x100f222e39e911f084c7005056c00001),
	(_binary 0x8faa6096794b4c57ac4fd8def3bd409c, 11.85, 1, _binary 0x06b2819412374aed8ea00c61f3a0e25a, _binary 0x100f2ec039e911f084c7005056c00001),
	(_binary 0x901978f5e9e94db7b1e3ed67bac6e84d, 28.00, 2, _binary 0x06b2819412374aed8ea00c61f3a0e25a, _binary 0x100f233839e911f084c7005056c00001),
	(_binary 0x9ee42ff57faa4bb7869e2a1dae20e050, 22.10, 2, _binary 0x98d001828c59446c88f4ccbb85304b2f, _binary 0x100f21d939e911f084c7005056c00001),
	(_binary 0xb633fcaf3e934ebb8611ea1739d2bde6, 28.00, 2, _binary 0x98d001828c59446c88f4ccbb85304b2f, _binary 0x100f2e2139e911f084c7005056c00001),
	(_binary 0xed4444748e9342e5a60c06557697b1ad, 10.85, 1, _binary 0x98d001828c59446c88f4ccbb85304b2f, _binary 0x100f188639e911f084c7005056c00001),
	(_binary 0xf90f0cfc3d494e64baa6e137f37a09d5, 31.90, 2, _binary 0x06b2819412374aed8ea00c61f3a0e25a, _binary 0x100f1ff539e911f084c7005056c00001);

-- Dumping structure for table webtreeshopdata.products
CREATE TABLE IF NOT EXISTS `products` (
  `product_id` binary(16) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `category` enum('FLOWER_SEEDS','FRUIT_TREE_SEEDS','HERB_SEEDS','VEGETABLE_SEEDS') DEFAULT NULL,
  `type` enum('ANNUAL_FLOWERS','CRUCIFEROUS_VEGETABLES','CULINARY_HERBS','EDIBLE_FLOWERS','FRUITING_VEGETABLES','LARGE_FRUIT_TREES','LEAFY_VEGETABLES','MEDICINAL_HERBS','ORNAMENTAL_TREES','PERENNIAL_FLOWERS','ROOT_VEGETABLES','SMALL_FRUIT_TREES') DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table webtreeshopdata.products: ~43 rows (approximately)
DELETE FROM `products`;
INSERT INTO `products` (`product_id`, `name`, `price`, `quantity`, `description`, `category`, `type`, `image_url`) VALUES
	(_binary 0x100f188639e911f084c7005056c00001, 'Carrot', 10.85, 100, 'Fresh carrot seeds for healthy root vegetables', 'VEGETABLE_SEEDS', 'ROOT_VEGETABLES', 'https://th.bing.com/th/id/OIP.TJqnAHySJZmW6X8alK7yeAHaEK?rs=1&pid=ImgDetMain'),
	(_binary 0x100f1ff539e911f084c7005056c00001, 'Tomato', 15.95, 120, 'Juicy tomato seeds, perfect for salads', 'VEGETABLE_SEEDS', 'FRUITING_VEGETABLES', 'https://th.bing.com/th/id/R.6a598d6c2d2879f33b04d2909d0d6e44?rik=TkYW5xK2%2fsP3ug&pid=ImgRaw&r=0'),
	(_binary 0x100f20ed39e911f084c7005056c00001, 'Cucumber', 13.99, 110, 'Cool cucumber seeds for refreshing vegetables', 'VEGETABLE_SEEDS', 'FRUITING_VEGETABLES', 'https://th.bing.com/th/id/R.a9b4553090eca67cec398b483cc8be73?rik=0P9MwdiMBbkPLA&pid=ImgRaw&r=0'),
	(_binary 0x100f218939e911f084c7005056c00001, 'Lettuce', 9.99, 150, 'Crisp lettuce seeds for fresh salads', 'VEGETABLE_SEEDS', 'LEAFY_VEGETABLES', 'https://th.bing.com/th/id/OIP.AQnp2U9D46xELf7zDCI5OwHaE7?rs=1&pid=ImgDetMain'),
	(_binary 0x100f21d939e911f084c7005056c00001, 'Onion', 11.05, 140, 'Sharp onion seeds for cooking', 'VEGETABLE_SEEDS', 'ROOT_VEGETABLES', 'https://th.bing.com/th/id/OIP.B0DKUvI_5htBrCKXQ_qtpgHaFj?rs=1&pid=ImgDetMain'),
	(_binary 0x100f222e39e911f084c7005056c00001, 'Chili Pepper', 14.50, 100, 'Spicy chili pepper seeds', 'VEGETABLE_SEEDS', 'FRUITING_VEGETABLES', 'https://th.bing.com/th/id/OIP.V2N8AwCmw1mURxwnvwqZSgHaEo?rs=1&pid=ImgDetMain'),
	(_binary 0x100f227939e911f084c7005056c00001, 'Kale', 13.00, 110, 'Healthy kale seeds rich in vitamins', 'VEGETABLE_SEEDS', 'LEAFY_VEGETABLES', 'https://th.bing.com/th/id/OIP.tvenu1vF33xo531wn3cpsgHaE7?rs=1&pid=ImgDetMain'),
	(_binary 0x100f22d339e911f084c7005056c00001, 'Beetroot', 12.00, 100, 'Sweet and earthy beetroot seeds', 'VEGETABLE_SEEDS', 'ROOT_VEGETABLES', 'https://th.bing.com/th/id/R.f9840ca970a9b2391bcd3ef400ce9850?rik=guGydkB7isV6WA&pid=ImgRaw&r=0'),
	(_binary 0x100f233839e911f084c7005056c00001, 'Zucchini', 14.00, 90, 'Easy to grow zucchini seeds', 'VEGETABLE_SEEDS', 'FRUITING_VEGETABLES', 'https://th.bing.com/th/id/OIP.4oGJ5YeLpO9ZX_vckhsGKAHaE8?rs=1&pid=ImgDetMain'),
	(_binary 0x100f237f39e911f084c7005056c00001, 'Pumpkin', 15.00, 80, 'Big and orange pumpkin seeds', 'VEGETABLE_SEEDS', 'FRUITING_VEGETABLES', 'https://blog.ltdcommodities.com/wp-content/uploads/2014/09/pumpkins.jpg'),
	(_binary 0x100f23c839e911f084c7005056c00001, 'Brussels Sprouts', 15.85, 120, 'Nutritious Brussels sprouts seeds', 'VEGETABLE_SEEDS', 'CRUCIFEROUS_VEGETABLES', 'https://th.bing.com/th/id/OIP.YqMwRpVPyOku32lkxnUHQQHaE5?rs=1&pid=ImgDetMain'),
	(_binary 0x100f242939e911f084c7005056c00001, 'Sweet Corn', 18.95, 130, 'Sweet corn seeds for summer harvest', 'VEGETABLE_SEEDS', 'FRUITING_VEGETABLES', 'https://th.bing.com/th/id/OIP.Cgw2V8p-ITV3BF6KCxy-5AHaFj?rs=1&pid=ImgDetMain'),
	(_binary 0x100f247439e911f084c7005056c00001, 'Radish', 9.99, 140, 'Crunchy radish seeds for salads', 'VEGETABLE_SEEDS', 'ROOT_VEGETABLES', 'https://th.bing.com/th/id/R.55e6eab2f18cb4d3e954900eb5ac6ed0?rik=QEEFQ0kUsp%2baqA&pid=ImgRaw&r=0'),
	(_binary 0x100f24c239e911f084c7005056c00001, 'Spinach', 11.99, 120, 'Nutritious spinach seeds for leafy greens', 'VEGETABLE_SEEDS', 'LEAFY_VEGETABLES', 'https://th.bing.com/th/id/OIP.jU_J5EZtsq7pzF-kgveovwHaEK?rs=1&pid=ImgDetMain'),
	(_binary 0x100f272739e911f084c7005056c00001, 'Carnation', 16.45, 70, 'Fragrant carnation flower seeds', 'FLOWER_SEEDS', 'PERENNIAL_FLOWERS', 'https://th.bing.com/th/id/R.ad0bd8fd55a3963eba2e4456fedac7ac?rik=0tztDTveAx0Tyw&pid=ImgRaw&r=0'),
	(_binary 0x100f27a339e911f084c7005056c00001, 'Daffodil', 14.95, 85, 'Bright yellow daffodil seeds', 'FLOWER_SEEDS', 'PERENNIAL_FLOWERS', 'https://th.bing.com/th/id/R.84aa300f2a0cb8b4f1362049f074ece7?rik=zaFswk4SSfvpyQ&riu=http%3a%2f%2fblog.longfield-gardens.com%2fwp-content%2fuploads%2f2017%2f04%2fDaffodil-Tahiti-Longfield-Gardens.jpg&ehk=YnQF2QwMj%2b630pny%2fbWzU0mvtjcOfs20xJqkDV70NQQ%3d&r'),
	(_binary 0x100f280439e911f084c7005056c00001, 'Pansy', 13.39, 95, 'Colorful pansy flower seeds', 'FLOWER_SEEDS', 'ANNUAL_FLOWERS', 'https://th.bing.com/th/id/OIP.d8hltHOCcQMOJexat9r_QgHaE6?rs=1&pid=ImgDetMain'),
	(_binary 0x100f285539e911f084c7005056c00001, 'Petunia', 12.50, 100, 'Lovely petunia flower seeds', 'FLOWER_SEEDS', 'ANNUAL_FLOWERS', 'https://th.bing.com/th/id/OIP.-naN63a4ZkczmvPsESdU-QHaFj?rs=1&pid=ImgDetMain'),
	(_binary 0x100f289d39e911f084c7005056c00001, 'Marigold', 12.95, 110, 'Bright orange marigold flower seeds', 'FLOWER_SEEDS', 'ANNUAL_FLOWERS', 'https://th.bing.com/th/id/OIP.rfyVGL5No-ZcHVkNwxOCMgHaFj?rs=1&pid=ImgDetMain'),
	(_binary 0x100f28ef39e911f084c7005056c00001, 'Sunflower', 14.50, 130, 'Tall sunflower seeds for sunny gardens', 'FLOWER_SEEDS', 'ANNUAL_FLOWERS', 'https://cdn.mos.cms.futurecdn.net/j9k8gWykScfsGNQCs6mJHo.jpg'),
	(_binary 0x100f293f39e911f084c7005056c00001, 'Lavender', 15.00, 90, 'Fragrant lavender flower seeds', 'FLOWER_SEEDS', 'PERENNIAL_FLOWERS', 'https://th.bing.com/th/id/R.628636b7f187f491c311fdbf4c74eacb?rik=6hfNMX4ahxNcZg&pid=ImgRaw&r=0'),
	(_binary 0x100f299339e911f084c7005056c00001, 'Rose', 20.00, 60, 'Classic red rose flower seeds', 'FLOWER_SEEDS', 'PERENNIAL_FLOWERS', 'https://th.bing.com/th/id/OIP.S0X8mU7oprCQjjnkK3wd6gHaFj?rs=1&pid=ImgDetMain'),
	(_binary 0x100f29e439e911f084c7005056c00001, 'Gardenia', 20.50, 70, 'Fragrant gardenia flower seeds', 'FLOWER_SEEDS', 'PERENNIAL_FLOWERS', 'https://th.bing.com/th/id/OIP.ZBBQ3WUo25_iRWnPNzWCjAHaE8?rs=1&pid=ImgDetMain'),
	(_binary 0x100f2a3c39e911f084c7005056c00001, 'Chrysanthemum', 13.69, 80, 'Colorful chrysanthemum flower seeds', 'FLOWER_SEEDS', 'PERENNIAL_FLOWERS', 'https://th.bing.com/th/id/R.6673d67788009bc4e0b8fec6d13662f2?rik=czUH4tbIX8lWWw&pid=ImgRaw&r=0'),
	(_binary 0x100f2b7839e911f084c7005056c00001, 'Begonia', 11.00, 90, 'Bright begonia flower seeds', 'FLOWER_SEEDS', 'ANNUAL_FLOWERS', 'https://th.bing.com/th/id/OIP.vOh3-n-UMwzaSQFceZn6ygHaE8?rs=1&pid=ImgDetMain'),
	(_binary 0x100f2bd139e911f084c7005056c00001, 'Zinnia', 12.00, 95, 'Vibrant zinnia flower seeds', 'FLOWER_SEEDS', 'ANNUAL_FLOWERS', 'https://th.bing.com/th/id/OIP.ltNCAYAMdRnfrxf2CD8y0wHaE8?rs=1&pid=ImgDetMain'),
	(_binary 0x100f2c2739e911f084c7005056c00001, 'Aster', 11.00, 85, 'Delicate aster flower seeds', 'FLOWER_SEEDS', 'PERENNIAL_FLOWERS', 'https://th.bing.com/th/id/R.54edd7e19b4d13af696f637ecfa4f024?rik=U1hbNlu2xwgmig&riu=http%3a%2f%2fwww.heronswood.com%2fwp-content%2fuploads%2f2017%2f04%2faster.jpg&ehk=Do1GhTR%2baKZg0zNSJIYYeMsOoocgevMiLYYQB9z0%2fGc%3d&risl=&pid=ImgRaw&r=0'),
	(_binary 0x100f2c6939e911f084c7005056c00001, 'Dahlia', 15.50, 70, 'Bright dahlia flower seeds', 'FLOWER_SEEDS', 'PERENNIAL_FLOWERS', 'https://th.bing.com/th/id/OIP.V6e_zeLTp9Z0_PBvKS2mBgHaE8?w=2260&h=1507&rs=1&pid=ImgDetMain'),
	(_binary 0x100f2cb539e911f084c7005056c00001, 'Impatiens', 11.00, 100, 'Colorful impatiens flower seeds', 'FLOWER_SEEDS', 'ANNUAL_FLOWERS', 'https://th.bing.com/th/id/OIF.8sZWjzLjgPRMPx7xBazSuQ?rs=1&pid=ImgDetMain'),
	(_binary 0x100f2ced39e911f084c7005056c00001, 'Fig Tree', 40.50, 40, 'Sweet fig tree seeds', 'FRUIT_TREE_SEEDS', 'LARGE_FRUIT_TREES', 'https://cdn.mos.cms.futurecdn.net/GmBEknxov7d4sACDd2ju7M-1024-80.jpg'),
	(_binary 0x100f2d1d39e911f084c7005056c00001, 'Pomegranate Tree', 42.25, 35, 'Fruitful pomegranate tree seeds', 'FRUIT_TREE_SEEDS', 'LARGE_FRUIT_TREES', 'https://www.thespruce.com/thmb/7CaieclZ-VI2KZNRqJAk-DJMuq4=/3658x2442/filters:no_upscale():max_bytes(150000):strip_icc()/pomegranate-growing-tips-3269232-04-486bb88091ad445b98bfd08ca2e2ebd1.jpg'),
	(_binary 0x100f2d4839e911f084c7005056c00001, 'Mulberry Tree', 35.75, 30, 'Juicy mulberry tree seeds', 'FRUIT_TREE_SEEDS', 'SMALL_FRUIT_TREES', 'https://th.bing.com/th/id/R.90b2e1cc8e38f4554caf9d9167987d3a?rik=uIWtfnqLBdQgaw&riu=http%3a%2f%2ftreepicturesonline.com%2fredmulberrytree.jpg&ehk=RdsnDlRVAeNKDajnLZVmaMiOGoDn9cPDL4OEL9awogM%3d&risl=&pid=ImgRaw&r=0'),
	(_binary 0x100f2d7239e911f084c7005056c00001, 'Almond Tree', 47.50, 25, 'Nut producing almond tree seeds', 'FRUIT_TREE_SEEDS', 'LARGE_FRUIT_TREES', 'https://th.bing.com/th/id/OIP.hrWLdaKHMuOBY_EQJrFXVQHaE8?rs=1&pid=ImgDetMain'),
	(_binary 0x100f2dbd39e911f084c7005056c00001, 'Rosemary Seed', 13.00, 150, 'Aromatic rosemary herb seeds', 'HERB_SEEDS', 'CULINARY_HERBS', 'https://th.bing.com/th/id/OIP.xVHHhOZQvrrJ2mB5tcoAJgHaEK?rs=1&pid=ImgDetMain'),
	(_binary 0x100f2e2139e911f084c7005056c00001, 'Sage Seed', 14.00, 140, 'Medicinal and culinary sage seeds', 'HERB_SEEDS', 'MEDICINAL_HERBS', 'https://th.bing.com/th/id/OIP.DHJ6XLL84wYete-d_aUJ-wHaHa?rs=1&pid=ImgDetMain'),
	(_binary 0x100f2e7039e911f084c7005056c00001, 'Chamomile Seed', 12.50, 130, 'Soothing chamomile herb seeds', 'HERB_SEEDS', 'MEDICINAL_HERBS', 'https://th.bing.com/th/id/OIP.Yy9s_WZk39MXtIjoeLipWAHaE7?rs=1&pid=ImgDetMain'),
	(_binary 0x100f2ec039e911f084c7005056c00001, 'Cilantro Seed', 11.85, 160, 'Fresh cilantro seeds for cooking', 'HERB_SEEDS', 'CULINARY_HERBS', 'https://cdn.mos.cms.futurecdn.net/uVPpVSUTevDVVSmYidCUxK-1920-80.jpg'),
	(_binary 0x100f2f1a39e911f084c7005056c00001, 'Fennel Seed', 12.50, 140, 'Anise-flavored fennel herb seeds', 'HERB_SEEDS', 'CULINARY_HERBS', 'https://th.bing.com/th/id/OIP.Cm_vcESG2gjYCT6XyJUGwwHaGJ?rs=1&pid=ImgDetMain'),
	(_binary 0x2b4104ef39ed11f084c7005056c00001, 'Nasturtium', 18.75, 50, 'Edible nasturtium flowers with a peppery flavor, great for salads and garnishes', 'FLOWER_SEEDS', 'EDIBLE_FLOWERS', 'https://th.bing.com/th/id/R.691b302ff4bfb64bd3a42b6e799e55ce?rik=ytAmyx3nBSL5Rw&pid=ImgRaw&r=0'),
	(_binary 0x2b41082a39ed11f084c7005056c00001, 'Calendula', 15.95, 60, 'Bright calendula edible flowers used for cooking and herbal teas', 'FLOWER_SEEDS', 'EDIBLE_FLOWERS', 'https://th.bing.com/th/id/R.1e4ebcaa853fb19f64d2bb8518c6364b?rik=Cb8OWXiKArIG1Q&pid=ImgRaw&r=0'),
	(_binary 0x2b41090039ed11f084c7005056c00001, 'Viola', 14.50, 55, 'Colorful viola flowers, edible and perfect for decorating desserts', 'FLOWER_SEEDS', 'EDIBLE_FLOWERS', 'https://cdn.britannica.com/72/117272-050-B76F5F9E/Garden-pansy.jpg');

-- Dumping structure for table webtreeshopdata.refresh_token
CREATE TABLE IF NOT EXISTS `refresh_token` (
  `rrfresh_tkid` binary(16) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `expires_at` datetime(6) DEFAULT NULL,
  `refresh_token_value` varchar(255) DEFAULT NULL,
  `accid` binary(16) NOT NULL,
  PRIMARY KEY (`rrfresh_tkid`),
  KEY `FKirrmvglsrjtgx8eexdfiyt8v4` (`accid`),
  CONSTRAINT `FKirrmvglsrjtgx8eexdfiyt8v4` FOREIGN KEY (`accid`) REFERENCES `account` (`accid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table webtreeshopdata.refresh_token: ~3 rows (approximately)
DELETE FROM `refresh_token`;
INSERT INTO `refresh_token` (`rrfresh_tkid`, `created_at`, `expires_at`, `refresh_token_value`, `accid`) VALUES
	(_binary 0x060555d44fe64ae0a0c665d31355daa5, '2025-05-26 15:49:31.000000', '2025-06-02 15:49:31.000000', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5OGQwMDE4Mi04YzU5LTQ0NmMtODhmNC1jY2JiODUzMDRiMmYiLCJpYXQiOjE3NDgyNDkzNzEsImV4cCI6MTc0ODg1NDE3MX0.gcRB9NKTVH_pxi9igUm3zChG-9Jm41piBa_NBfW2wPU', _binary 0x98d001828c59446c88f4ccbb85304b2f),
	(_binary 0x324ff56e85a64286965a7db5f3f1a0d2, '2025-05-27 00:01:06.000000', '2025-06-03 00:01:06.000000', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5MzhiNmY2ZS03MGJkLTQwODktODQ0Yy03NDUxNTE0MmQ1NGIiLCJpYXQiOjE3NDgyNzg4NjYsImV4cCI6MTc0ODg4MzY2Nn0.s0YyyOtvBva3uy9h8N0M03ExtWPn7mtcsGJA-xhxNjA', _binary 0x938b6f6e70bd4089844c74515142d54b),
	(_binary 0x8fad8bfc5c224b0498e2e7886b912beb, '2025-05-27 00:05:47.000000', '2025-06-03 00:05:47.000000', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwNmIyODE5NC0xMjM3LTRhZWQtOGVhMC0wYzYxZjNhMGUyNWEiLCJpYXQiOjE3NDgyNzkxNDcsImV4cCI6MTc0ODg4Mzk0N30.Zlkw-bgPhnO3GTXPOvqZPSDFkhCdtBrgik50JcT99i4', _binary 0x06b2819412374aed8ea00c61f3a0e25a);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
