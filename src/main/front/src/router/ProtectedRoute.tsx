import { isUserAuthenticated } from "@/auth";
import { Navigate } from "react-router-dom";

interface ProtectedRouteProps {
  children: React.ReactNode;
}

export function ProtectedRoute({ children }: ProtectedRouteProps) {
  if (!isUserAuthenticated()) return <Navigate to="/signin" replace />;

  return <>{children}</>;
}
