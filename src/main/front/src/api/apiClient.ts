import { getToken } from "@/auth";
import type { n, s } from "../types";
import { API_BASE_URL } from "./endpoints";

export interface ApiOpts {
  method: "GET" | "POST" | "PUT" | "DELETE";
  body?: s;
  headers?: Record<s, s>;
  queryParams?: Record<s, s | n>;
}

export async function apiClient<T>(endpoint: s, options: ApiOpts): Promise<T> {
  const url = new URL(`${API_BASE_URL}${endpoint}`);

  if (options.queryParams) {
    for (const [key, value] of Object.entries(options.queryParams)) {
      url.searchParams.append(key, String(value));
    }
  }

  const headers: Record<s, s> = {
    "Content-Type": "application/json",
    ...options.headers,
  };

  const token = getToken();
  if (token) headers.Authorization = `Bearer ${token}`;

  const res = await fetch(url.toString(), {
    ...options,
    headers,
  });

  if (!res.ok) {
    const error = await res.json();
    throw new Error(error.message || "An error occurred");
  }

  return res.json();
}
