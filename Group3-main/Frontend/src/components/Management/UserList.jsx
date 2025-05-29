import { useManage } from "../../context/UseManageContext";
import "../../components/Management/UserList"
import UserCard from "./UserCard";
const UserList = () => {
  const { users } = useManage();

  return (
    <div className="user-list">
      {users.length > 0 ? (
        users.map((user) => <UserCard key={user.id} user={user} />)
      ) : (
        <p className="no-user">No users found.</p>
      )}
    </div>
  );
};

export default UserList;