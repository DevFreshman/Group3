import React, { useEffect } from "react";
import { useAuth } from "../context/UseAuthContext";
import { Outlet, useNavigate } from "react-router-dom";

const PrivateRoute = ({ roles }) => {
  const { user, loading } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (loading) return; // Đợi load xong

    if (!user) {
      console.log("No user, redirecting");
      navigate("/");
    } else if (roles && !roles.includes(user.roles)) {
      console.log("Role mismatch, redirecting");
      navigate("/notFound");
    }
  }, [user, roles, loading, navigate]);

  if (loading) return null; // Tránh render khi chưa load xong

  return <Outlet />;
};

export default PrivateRoute;
