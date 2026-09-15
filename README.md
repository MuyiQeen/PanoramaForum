# PanoramaForum

## 数据库设计

### user表

| User表   | 数据库对应字段名称 | 数据类型      | 备注           |
| -------- | ------------------ | ------------- | -------------- |
| 编号     | user_id            | int           | 系统分配       |
| 用户名   | username           | String        |                |
| 邮箱     | email              | String        | 必须           |
| 密码     | passwd             | String        | 必须；哈希存储 |
| 头像路径 | avatar_path        | String        |                |
| 角色     | role               | Enum          | 必须           |
| 软删除   | deleted            | Boolean       | 默认：FALSE    |
| 禁用     | disabled           | Boolean       | 默认：FALSE    |
| 创建时间 | created_at         | LocalDateTime | 自动填充       |
| 修改时间 | updated_at         | LocalDateTime | 自动填充       |
| 积分     | points             | Long          | 默认：0        |
| 性别     | sex                | Enum          | 默认：未知     |
| 关注数量 | following_count    | int           | 默认：0        |
| 粉丝数量 | follower_count     |               |                |

### id_pool池表

id池用于存储预生成的id

| id_pool表 | 数据库对应字段名称 | 数据类型      | 备注                        |
| --------- | ------------------ | ------------- | --------------------------- |
| 用户id    | user_id            | int           | 按序生成，打乱插入；最小6位 |
| 是否使用  | used               | boolean       | 默认：false                 |
| 创建时间  | created_at         | LocalDateTime | 自动填充                    |
| 修改时间  | updated_at         | LocalDateTime | 自动填充                    |

------

### board表

板块表用于存储板块信息

| Board表  | 数据库对应字段名称 | 数据类型      | 备注                                  |
| -------- | ------------------ | ------------- | ------------------------------------- |
| 编号     | board_id           | int           | 自增                                  |
| 板块名称 | board_name         | String        | 创建者定义                            |
| 创建者ID | creator_id         | int           | 创建者ID                              |
| 描述     | description        | String        | 默认值：“贴主太懒了，什么都没有留下~” |
| 总贴数   | post_count         | int           | 默认0                                 |
| 浏览量   | view_count         | Long          | 默认0                                 |
| 排序权重 | sort_order         | int           | 默认0                                 |
| 创建日期 | created_at         | LocalDateTime | 自动填充                              |
| 修改时间 | updated_at         | LocalDateTime | 自动填充                              |

------

### post表

| post表       | 数据库对应字段名称 | 数据类型      | 备注     |
| ------------ | ------------------ | ------------- | -------- |
| 编号         | post_id            | int           | 自增     |
| 所属板块     | board_id           | int           | 板块编号 |
| 贴子标题     | post_title         | String        | 必须     |
| 帖子内容     | post_content       | String        | 必须     |
| 发帖人id     | user_id            | int           | 必须     |
| 浏览量       | view_count         | Long          | 默认：0  |
| 已有回复数量 | reply_count        | int           | 默认：0  |
| 点赞量       | like_count         | int           | 默认：0  |
| 点踩量       | dislike_count      | int           | 默认：0  |
| 收藏量       | favorite_count     | int           | 默认：0  |
| 转发量       | share_count        | int           | 默认：0  |
| 发帖时间     | created_at         | LocalDateTime | 自动填充 |
| 是否顶置     | pinned             | Boolean       | FALSE    |
| 是否精华     | featured           | Boolean       | FALSE    |
| 标签         | tag                | String        | null     |
| 创建时间     | created_at         | LocalDateTime | 自动填充 |
| 修改时间     | updated_at         | LocalDateTime | 自动填充 |

-------

### reply表

reply表用于存储帖子下面的子回复和孙回复

| reply表      | 数据库对应字段名称 | 数据类型      | 备注        |
| ------------ | ------------------ | ------------- | ----------- |
| 编号         | reply_id           | int           |             |
| 所属帖子     | post_id            | int           |             |
| 父回复ID     | parent_id          | int           |             |
| 被回复者ID   | reply_to_id        | int           |             |
| 回复者ID     | reporter_id        | int           |             |
| 回复正文     | content            | String        |             |
| 已有回复数量 | reply_count        | int           |             |
| 点赞量       | like_count         | int           |             |
| 点踩量       | dislike_count      | int           |             |
| 是否神评     | hoted              | int           |             |
| 回复时间     | created_at         | LocalDateTime | 自动填充    |
| 软删除       | deleted            | Boolean       | 默认：FALSE |

------

### tag表

tag表用于存储帖子的话题标签

| tag表        | 数据库对应字段名称 | 数据类型      | 备注          |
| ------------ | ------------------ | ------------- | ------------- |
| 标签ID       | tag_id             | int           | 自增          |
| 标签名称     | tag_name           | String        |               |
| 标签背景颜色 | tag_bgc            | String        | 默认：#808080 |
| 标签字体颜色 | tag_color          | String        | 默认：#000000 |
| 创建时间     | created_at         | LocalDateTime | 自动填充      |
| 修改时间     | updated_at         | LocalDateTime | 自动填充      |

-------

### share表

share表用于存储用户是否转发帖子，防止重复转发

| share表  | 数据库对应字段名称 | 数据类型      | 备注     |
| -------- | ------------------ | ------------- | -------- |
| 转发人   | user_id            | int           | 转发人ID |
| 帖子ID   | post_id            | int           | 帖子ID   |
| 创建时间 | created_at         | LocalDateTime | 自动填充 |
| 修改时间 | updated_at         | LocalDateTime | 自动填充 |

-------

### follow_user表

follow_user表用于存储用用户单向关注、拉黑用户的关系

| follow_user表 | 数据库对应字段名称 | 数据类型      | 备注     |
| ------------- | ------------------ | ------------- | -------- |
| 用户ID        | user_id            | int           |          |
| 关注ID        | following_id       | int           |          |
| 状态          | status             | Enum          |          |
| 创建时间      | created_at         | LocalDateTime | 自动填充 |
| 修改时间      | updated_at         | LocalDateTime | 自动填充 |

------

### follow_board表

follow_user表用于存储用用户关注的板块

| follow_board表 | 数据库对应字段名称 | 数据类型      | 备注          |
| -------------- | ------------------ | ------------- | ------------- |
| 用户ID         | user_id            | int           |               |
| 板块ID         |                    | int           | board_id != 0 |
| 状态           | status             | Enum          |               |
| 创建时间       | created_at         | LocalDateTime | 自动填充      |
| 修改时间       | updated_at         | LocalDateTime | 自动填充      |

-------

### follow_post表

follow_post表用于存储用户收藏的帖子

| follow_post表 | 数据库对应字段名称 | 数据类型      | 备注         |
| ------------- | ------------------ | ------------- | ------------ |
| 用户ID        | user_id            | int           |              |
| 帖子ID        | post_id            | int           | post_id != 0 |
| 创建时间      | created_at         | LocalDateTime | 自动填充     |
| 修改时间      | updated_at         | LocalDateTime | 自动填充     |

-------

### like_post表

用于存储用户喜欢的帖子

| like_post表 | 数据库对应字段名称 | 数据类型      | 备注                       |
| ----------- | ------------------ | ------------- | -------------------------- |
| 用户ID      | user_id            | int           |                            |
| 帖子ID      | post_id            | int           | post_id != 0               |
| 回复ID      | reply_id           | int           | 当reply_id=0时收藏的是帖子 |
| 状态        | status             | Enum          |                            |
| 创建时间    | created_at         | LocalDateTime | 自动填充                   |
| 修改时间    | updated_at         | LocalDateTime | 自动填充                   |

------

## 功能设计

注册账户

