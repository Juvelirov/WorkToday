import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import AdminPage from "./pages/AdminPage";
import { InternshipPage } from "./pages/InternshipPage";
import { InternshipSearchPage } from "./pages/InternshipSearchPage";
import { LandingPage } from "./pages/LandingPage";
import { SignInForm } from "./pages/SignInForm";
import SignUpForm from "./pages/SignUpForm";
import { ProtectedRoute } from "./router/ProtectedRoute";
import { HrProfilePage, InternProfilePage } from "./pages/ProfilePage";
import AnalyticsPage from "./pages/AnalyticsPage";
import InternshipCreationPage from "./pages/InternshipCreationPage";
import AnotherUserProfilePage from "./pages/AnotherProfilePage";
import NoAuthPage from "./pages/NoAuthPage";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/signup" element={<SignUpForm />} />
        <Route path="/signin" element={<SignInForm />} />
        <Route
          path="/internProfile"
          element={
            <ProtectedRoute allowedRoles={["ROLE_STUDENT", "ROLE_ADMIN"]}>
              <InternProfilePage />
            </ProtectedRoute>
          }
        />
        <Route
          path="/hrProfile"
          element={
            <ProtectedRoute allowedRoles={["ROLE_HR", "ROLE_ADMIN"]}>
              <HrProfilePage />
            </ProtectedRoute>
          }
        />
        <Route
          path="/profile/:email"
          element={
            <ProtectedRoute>
              <AnotherUserProfilePage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/internshipsSearch"
          element={
            <ProtectedRoute>
              <InternshipSearchPage />
            </ProtectedRoute>
          }
        />
        <Route
          path="/internship/:id"
          element={
            <ProtectedRoute>
              <InternshipPage />
            </ProtectedRoute>
          }
        />
        <Route
          path="/internshipCreate"
          element={
            <ProtectedRoute allowedRoles={["ROLE_HR", "ROLE_ADMIN"]}>
              <InternshipCreationPage />
            </ProtectedRoute>
          }
        />
        <Route
          path="/analytics"
          element={
            <ProtectedRoute allowedRoles={["ROLE_HR", "ROLE_ADMIN"]}>
              <AnalyticsPage />
            </ProtectedRoute>
          }
        />
        <Route path="/no-auth" element={<NoAuthPage />} />
        <Route
          path="/admin"
          element={
            <ProtectedRoute allowedRoles={["ROLE_ADMIN"]}>
              <AdminPage />
            </ProtectedRoute>
          }
        />
      </Routes>
    </Router>
  );
}
