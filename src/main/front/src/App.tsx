import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import AdminPage from "./pages/AdminPage";
import { InternshipPage } from "./pages/InternshipPage";
import { InternshipSearchPage } from "./pages/InternshipSearchPage";
import { LandingPage } from "./pages/LandingPage";
import { SignInForm } from "./pages/SignInForm";
import { SignUpForm } from "./pages/SignUpForm";
import { ProtectedRoute } from "./router/ProtectedRoute";
import { InternProfilePage } from "./pages/ProfilePage";
import AnalyticsPage from "./pages/AnalyticsPage";
import { InternshipCreationPage } from "./pages/InternshipCreationPage";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route
          path="/internProfile"
          element={
            <ProtectedRoute>
              <InternProfilePage />
            </ProtectedRoute>
          }
        />
        {/* <Route
          path="/hrProfile"
          element={
            <ProtectedRoute>
              <HrProfilePage />
            </ProtectedRoute>
          }
        /> */}
        <Route path="/signin" element={<SignInForm />} />
        <Route path="/signup" element={<SignUpForm />} />
        <Route
          path="/internshipsSearch"
          element={
            <ProtectedRoute>
              <InternshipSearchPage />
            </ProtectedRoute>
          }
        />
        <Route
          path="/internship"
          element={
            <ProtectedRoute>
              <InternshipPage />
            </ProtectedRoute>
          }
        />
        <Route
          path="/internshipCreate"
          element={
            <ProtectedRoute>
              <InternshipCreationPage />
            </ProtectedRoute>
          }
        />
        <Route
          path="/analytics"
          element={
            <ProtectedRoute>
              <AnalyticsPage />
            </ProtectedRoute>
          }
        />
        <Route
          path="/admin"
          element={
            <ProtectedRoute>
              <AdminPage />
            </ProtectedRoute>
          }
        />
        <Route path="/landing" element={<LandingPage />} />
      </Routes>
    </Router>
  );
}
