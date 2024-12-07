import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import { SignInForm } from "./pages/SignInForm";
import { SignUpForm } from "./pages/SignUpForm";
import { StudentPrivatePage } from "./pages/StudentPrivatePage";
import { ProtectedRoute } from "./router/ProtectedRoute";
import AdminPage from "./pages/AdminPage";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path="/signin" element={<SignInForm />} />
        <Route path="/signup" element={<SignUpForm />} />
        {/* <Route path="/vacancies" element={<VacanciesPage />} /> */}
        <Route
          path="/"
          element={
            <ProtectedRoute>
              <StudentPrivatePage />
            </ProtectedRoute>
          }
        />
        <Route path="/admin" element={<AdminPage />} />
      </Routes>
    </Router>
  );
}
