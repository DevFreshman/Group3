import "../../assets/Styles/Management/UserCard.css"
const UserCard = ({ user }) => {
  return (
    <div className="user-card">
      <h4>{user.name}</h4>
      <p>{user.email}</p>
      <span className="role-badge">{user.role}</span>
    </div>
  );
};

export default UserCard;