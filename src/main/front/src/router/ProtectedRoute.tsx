import { getRole, isUserAuth } from "@/auth";
import { s } from "@/types";
import { Navigate } from "react-router-dom";

interface ProtectedRouteProps {
  children: React.ReactNode;
  allowedRoles?: s[];
}

export function ProtectedRoute(p: ProtectedRouteProps) {
  if (!isUserAuth()) return <Navigate to="/signin" replace />;

  if (p.allowedRoles) {
    const userRole = getRole();
    if (!userRole || !p.allowedRoles.includes(userRole)) {
      return <Navigate to="/no-auth" replace />;
    }
  }

  return <>{p.children}</>;
}
