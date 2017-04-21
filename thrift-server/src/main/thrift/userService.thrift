namespace java com.github.afterloe.domain
struct User {
	1: i32 id
	2: string username
	3: i32 age
}

service userService {
	list<User> getUsers();
	User registerUser(1:string username, 2:i32 age);
}
