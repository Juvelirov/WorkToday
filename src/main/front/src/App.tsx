import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import { SignInForm } from "./pages/SignInForm";
import { SignUpForm } from "./pages/SignUpForm";
import { StudentPrivatePage } from "./pages/StudentPrivatePage";
import { ProtectedRoute } from "./router/ProtectedRoute";
import AdminPage from "./pages/AdminPage";
import { VacanciesPage } from "./pages/VacanciesPage";
import { KnowledgeBasePage } from "./pages/KnowledgeBasePage";
import { LandingPage } from "./pages/LandingPage";

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
          path="/vacancies"
          element={
            <ProtectedRoute>
              <VacanciesPage />
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
