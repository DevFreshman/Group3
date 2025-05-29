import { useManage } from "../../context/UseManageContext";

const Manager = () => {
  const { searchTerm, setSearchTerm, roleFilter, setRoleFilter } = useManage();

  return (
    <div className="sidebar-management">
      <h3>Management user</h3>
      <input
        type="text"
        placeholder="Search by name"
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />
      <select value={roleFilter} onChange={(e) => setRoleFilter(e.target.value)}>
        <option value="">All Roles</option>
        <option value="admin">Admin</option>
        <option value="user">User</option>
      </select>
    </div>
  );
};

export default Manager;