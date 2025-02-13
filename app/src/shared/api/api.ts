import axios, { AxiosInstance } from "axios";
import { authFormData } from "../types/types";

const createApiInstance = (url: string): AxiosInstance => {
    const api = axios.create({
        baseURL: process.env.NEXT_PUBLIC_URL + url,
        headers: { Accept: "application/json" }
    });

    api.interceptors.request.use((config) => {
        const token = getToken();
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    });

    return api;
}

const getToken = (): string | null => {
    if (typeof window === "undefined") return null; // Evita erro no SSR
    return localStorage.getItem("token");
};

const verifyToken = (token?: string): void => {
    if (!token) return;
    if (typeof window !== "undefined") {
        localStorage.setItem("token", token);
    }
};

export class Api {
    private api: AxiosInstance;

    constructor(url: string) {
        this.api = createApiInstance(url);
    }

    async login(user: authFormData): Promise<{ userName: string, token: string }> {
        const res = await this.api.post("/login", user);
        verifyToken(res?.data?.token);
        return res?.data
    }

    async register(newUser: authFormData): Promise<{ userName: string, token: string }> {
        const res = await this.api.post("/register", newUser);
        verifyToken(res?.data?.token);
        return res.data;
    }
}

export const authApi = new Api("/auth");