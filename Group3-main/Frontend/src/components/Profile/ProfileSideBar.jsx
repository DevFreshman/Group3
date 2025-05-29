import React from "react";

const ProfileSidebar = ({ userInfo, tab, onSelectTab }) => {
  return (
    <aside className="profile-sidebar">
      <div className="profile-summary">
        <img src={userInfo.avatar} alt="Avatar" className="avatar" />
        <h2 className="sidebar-name">{userInfo.name}</h2>
        <p className="sidebar-email">{userInfo.email}</p>
      </div>
      <ul className="sidebar-menu">
        <li className={tab === "info" ? "active" : ""} onClick={() => onSelectTab("info")}>User Information</li>
        <li className={tab === "history" ? "active" : ""} onClick={() => onSelectTab("history")}>Transaction History</li>
        <li className={tab === "notify" ? "active" : ""} onClick={() => onSelectTab("notify")}>Notification</li>
        <li className={tab === "privacy" ? "active" : ""} onClick={() => onSelectTab("privacy")}>Privacy Setting</li>
      </ul>
    </aside>
  );
};

export default ProfileSidebar;
