"use client"
import { useMutation, useQueryClient } from "@tanstack/react-query"
import { categoryFormData } from "../schemas/types/types";
import { categoryApi } from "../api/api";
import { toast } from "react-toastify";
import { AxiosError } from "axios";
import { IPagedResponse } from "../schemas/types/IPagedResponse";

export const useCategoryMutations = () => {
    const queryClient = useQueryClient();

    const createCategoryMutation = useMutation({
        mutationFn: (data: categoryFormData) => categoryApi.createCategory(data),
        onSuccess: (newCategory) => {
            const category = newCategory.data;

            queryClient.setQueryData<IPagedResponse<categoryFormData[]>>(
                ["categories"],
                (oldData) => {
                    if (oldData) {
                        return {
                            content: [...oldData.content, category],
                            page: oldData.page,
                        };
                    }

                    return {
                        content: [category],
                        page: { size: 1, number: 0, totalElements: 1, totalPages: 1 },
                    };
                }
            );
        },
        onError: (error) => {
            if (error instanceof AxiosError) {
                toast.error(error.response?.data?.error || "Erro na requisição Post.");
            }
        },
    });


    return {
        createCategoryMutation
    }
}