import type { s } from "./types";

export const isUserAuth = () => !!getToken();

export const saveBasicInfo = (token: s, role: s) => {
  localStorage.setItem("token", token);
  localStorage.setItem("role", role);
};

export function signout() {
  window.location.href = "/signin";
  localStorage.removeItem("token");
  localStorage.removeItem("role");
  localStorage.removeItem("imgUrl");
}

export const getRole = () => localStorage.getItem("role");
export const getToken = () => localStorage.getItem("token");
export const setToken = (v: s) => {
  localStorage.setItem("role", "ROLE_STUDENT");
  localStorage.setItem("token", v);
};
