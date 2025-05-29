import React, { useEffect, useState } from "react";
import "../assets/Styles/Profile/Profile.css";
import { getUserDetailAPI, saveUserDetailAPI } from "../api/profileAPI";
import { useAuth } from "../context/UseAuthContext";
import ProfileSidebar from "../components/Profile/ProfileSideBar";
import UserInfo from "../components/Profile/UserInfo";
import TransactionHistory from "../components/Profile/TransactionHistory";
import Notifications from "../components/Profile/Notifications";
import PrivacySettings from "../components/Profile/PrivacySettings";
import { toast } from "react-toastify";

const Profile = () => {
  const { user, loading } = useAuth();
  const [userInfo, setUserInfo] = useState({
    avatar: null,
    name: null,
    email: null,
    dateOfBirth: null,
    phone: null,
    address: null,
  });

  const [tab, setTab] = useState("info");
  const [editField, setEditField] = useState(null);
  const [editValues, setEditValues] = useState({});

  const fetchUserData = async () => {
    try {
      const response = await getUserDetailAPI();
      setUserInfo(response);
    } catch (error) {
      console.error("Error fetching user data:", error);
    }
  };

  useEffect(() => {
    if (loading || !user) return;
    fetchUserData();
  }, [user, loading]);

  const toggleEdit = (field) => {
    if (editField !== field) {
      setEditValues({ [field]: userInfo[field] });
      setEditField(field);
    } else {
      setEditField(null);
    }
  };

  const handleChange = (field, value) => {
    setEditValues((prev) => ({ ...prev, [field]: value }));
  };

  const handleSave = async (field) => {
    try {
      const newValue = editValues[field];
      await saveUserDetailAPI(field, newValue);
      setUserInfo((prev) => ({ ...prev, [field]: newValue }));
      setEditField(null);
      setEditValues({});
      toast.success("Save successful!")
    } catch (error) {
      console.error("Error saving data:", error);
    }
  };

  const handleCancel = () => {
    setEditField(null);
    setEditValues({});
  };

  const renderTabContent = () => {
    switch (tab) {
      case "info":
        return (
          <UserInfo
            userInfo={userInfo}
            editField={editField}
            editValues={editValues}
            onEdit={toggleEdit}
            onChange={handleChange}
            onSave={handleSave}
            onCancel={handleCancel}
          />
        );
      case "history":
        return <TransactionHistory />;
      case "notify":
        return <Notifications />;
      case "privacy":
        return <PrivacySettings />;
      default:
        return null;
    }
  };

  return (
    <div className="profile-container">
      <ProfileSidebar userInfo={userInfo} tab={tab} onSelectTab={setTab} />
      <main className="profile-main">{renderTabContent()}</main>
    </div>
  );
};

export default Profile;
