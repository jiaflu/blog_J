####MySQL中反引号(``)的作用
- 为了区分MySQL的保留字与普通字符而引入的；
- 不加反引号建的表不能包含MySQL保留字，否则会出错；

```
ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1
```
- ENGINE=InnoDB：使用InnoDB引擎，InnoDB，是MySQL的数据库引擎之一，为MySQL AB发布binary的标准之一；
- DEFAULT CHARSET=utf8 数据库默认编码为utf-8；
- AUTO_INCREMENT=1 自增键的起始