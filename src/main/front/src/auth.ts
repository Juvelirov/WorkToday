import type { b } from "./types";

export function isUserAuthenticated(): b {
  // const token = localStorage.getItem("token");
  // return !!token;
  return true;
}

export function signout() {
  localStorage.removeItem("token");
  window.location.href = "/signin";
}

// export function setUserAuthToken(token: string) {
//   localStorage.setItem("token", token);
// }

// export function clearAuth() {
//   localStorage.removeItem("token");
// }
