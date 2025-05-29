import React from "react";

const UserInfo = ({ userInfo, editField, editValues, onEdit, onChange, onSave, onCancel }) => {
  const fields = [
    { label: "Full Name", key: "name" },
    { label: "Date Of Birth", key: "dateOfBirth", type: "date" },
    { label: "Phone", key: "phone" },
    { label: "Address", key: "address" },
  ];

  return (
    <div className="profile-details">
      {fields.map(({ label, key, type }) => {
        const isEditing = editField === key;
        return (
          <div className="detail-row" key={key}>
            <span className="label">{label}</span>
            <div className="value">
              {isEditing ? (
                <>
                  <input
                    type={type || "text"}
                    className="input-field"
                    value={editValues[key] || ""}
                    onChange={(e) => onChange(key, e.target.value)}
                  />
                  <button className="btn save" onClick={() => onSave(key)}>Save</button>
                  <button className="btn cancel" onClick={onCancel}>Cancel</button>
                </>
              ) : (
                <>
                  <span>{userInfo[key]}</span>
                  <button className="btn edit" onClick={() => onEdit(key)}>Edit</button>
                </>
              )}
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default UserInfo;
