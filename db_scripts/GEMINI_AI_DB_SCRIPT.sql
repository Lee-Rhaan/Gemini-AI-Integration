CREATE DATABASE IF NOT EXISTS gemini_ai;
USE gemini_ai;

DROP TABLE IF EXISTS gemini_config;
CREATE TABLE gemini_config
(
    config_id    int(11) NOT NULL AUTO_INCREMENT,
    config_key   varchar(100)  NOT NULL,
    config_value varchar(2000) NOT NULL,
    PRIMARY KEY (config_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gemini_config
-- ----------------------------
insert into gemini_config(config_key, config_value)
values ('API_KEY', 'Paste your api key here');
commit;