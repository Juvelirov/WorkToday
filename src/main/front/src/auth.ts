import type { b } from "./types";

export function isUserAuthenticated(): b {
  return !!localStorage.getItem("token");
  // return true;
}

export function signout() {
  localStorage.removeItem("token");
  window.location.href = "/signin";
}
