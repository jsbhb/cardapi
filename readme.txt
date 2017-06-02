/**
 * 一、restful API设计模板：
 * 	基本形式：/version/Resources/parameter
 * 	1、version 表示该接口版本号，用来后期接口升级兼容新老版本
 * 	2、Resources 表示资源，如users 使用复数形式
 * 	3、parameter  表示条件，如ID
 * 
 * 	get请求：
 * 		用来获取数据，参考URL：http://127.0.0.1:8080/tradeCity/1.0/users，
 * 		查询请求统一带上分页条件，currentPage：当前页；numPerPage：每页条数；由于查询可能分很多条件，所以在url中不明确表示，参数统一用对象形式，以json格式传输；
 * 	post请求：
 * 		用来创建数据，参考URL：http://127.0.0.1:8080/tradeCity/1.0/user，对象参数以json格式
 * 	put请求：
 * 		用来更新数据，参考URL：http://127.0.0.1:8080/tradeCity/1.0/user/1，更新ID为1的用户；更新为整个对象，所以前台必须传整个对象，属性没有传完整会更新为空
 *  patch请求：
 * 		用来更新数据，参考URL：http://127.0.0.1:8080/tradeCity/1.0/user/1，更新ID为1的用户；更新为部分对象，空属性不更新
 * 	delete请求:
 * 		用来删除数据，参考URL：http://127.0.0.1:8080/tradeCity/1.0/user/1，删除ID为1的用户
 * 
 * 二、返回请求统一使用 CallBackModel 对象；分页model统一：Pagination
 * 
 * 三、为了后期拆分微服务时能够更加方便，在开发过程中
 * 		1、每个服务里面不能出现其他服务的mapper
 * 		2、每个服务里面的查询涉及到不同服务的数据表时不能采用连表查询
 *      3、明显服务于每个服务的常量分开放在每个服务中，不要统一放在一个constants
 *      4、应用内统一常量，统一管理，但要按常量功能区分
 *      5、类内常量直接定义在类中
 *
 * 四、目前各个package对应的服务
 * 		1、administrator  后台服务
 * 		2、associator     入驻会员服务
 * 		3、basic			    基本类，如分页model，统一返回请求的model
 * 		4、statistics	    统计服务
 * 		5、product        产品服务
 *      6、common         公共服务
 *
 *
 * 五、所有业务逻辑写在service，controller只做简单的接收数据和封装返回数据
 *
 *
 * 六、案例看test包
 *
 */