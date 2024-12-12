import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import AdminPage from "./pages/AdminPage";
import { InternshipSearchPage } from "./pages/InternshipSearchPage";
import { KnowledgeBasePage } from "./pages/KnowledgeBasePage";
import { LandingPage } from "./pages/LandingPage";
import { SignInForm } from "./pages/SignInForm";
import { SignUpForm } from "./pages/SignUpForm";
import { StudentPrivatePage } from "./pages/StudentPrivatePage";
import { ProtectedRoute } from "./router/ProtectedRoute";
import { InternshipPage } from "./pages/InternshipPage";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route
          path="/"
          element={
            <ProtectedRoute>
              <StudentPrivatePage />
            </ProtectedRoute>
          }
        />
        <Route path="/signin" element={<SignInForm />} />
        <Route path="/signup" element={<SignUpForm />} />
        <Route
          path="/internships"
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
          path="/base"
          element={
            <ProtectedRoute>
              <KnowledgeBasePage />
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
