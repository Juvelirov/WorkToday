import type { s } from "./types";

export const isUserAuthenticated = () => !!getToken();

export function signout() {
  localStorage.removeItem("token");
  window.location.href = "/signin";
}

export const getToken = () => localStorage.getItem("token");
export const setToken = (v: s) => localStorage.setItem("token", v);
