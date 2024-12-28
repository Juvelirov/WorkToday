import type { s } from "./types";

export const isUserAuthenticated = () => !!getToken();

export function signout() {
  localStorage.removeItem("token");
  localStorage.removeItem("role");
  window.location.href = "/signin";
}

export const getToken = () => localStorage.getItem("token");
export const setToken = (v: s) => {
  localStorage.setItem("role", "ROLE_STUDENT");
  localStorage.setItem("token", v);
};
