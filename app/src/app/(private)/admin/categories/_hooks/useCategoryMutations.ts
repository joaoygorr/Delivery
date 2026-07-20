import { useMutation, useQueryClient } from "@tanstack/react-query"
import { categoryFormData } from "@/shared/types/types";
import { categoryApi } from "@/shared/api/api";
import { toast } from "react-toastify";
import { AxiosError } from "axios";
import { IPagedResponse } from "@/shared/types/IPagedResponse";

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
                        page: {
                            size: 1,
                            number: 0,
                            totalElements: 1,
                            totalPages: 1,
                        },
                    };
                }
            );
            toast.success("Categoria criada com sucesso!");
        },
        onError: (error) => {
            if (error instanceof AxiosError) {
                toast.error(error.response?.data?.error || "Erro na requisição.");
            }
        },
    });

    const updateCategoryMutation = useMutation({
        mutationFn: (data: categoryFormData) => categoryApi.updateCategory(data),
        onSuccess: (updatedCategory) => {
            const category = updatedCategory.data;

            queryClient.setQueryData<IPagedResponse<categoryFormData[]>>(
                ["categories"],
                (oldData) => {
                    if (!oldData) return oldData;

                    return {
                        ...oldData,
                        content: oldData.content.map((item) =>
                            item.id === category.id ? category : item
                        ),
                    };
                }
            );
            toast.success("Categoria atualizada com sucesso!");
        },
        onError: (error) => {
            if (error instanceof AxiosError) {
                toast.error(error.response?.data?.error || "Erro na requisição.");
            }
        },
    });

    const deleteCategoryMutation = useMutation({
        mutationFn: (id: number) => categoryApi.deleteObject(id),
        onSuccess: (_, id) => {
            queryClient.setQueryData<IPagedResponse<categoryFormData[]>>(
                ["categories"],
                (oldData) => {
                    if (!oldData) return oldData;
                    return {
                        ...oldData,
                        content: oldData.content.filter((item) => item.id !== id),
                    };
                }
            );
            toast.success("Categoria excluída com sucesso!");
        },
        onError: (error) => {
            if (error instanceof AxiosError) {
                toast.error(error.response?.data?.error || "Erro ao excluir categoria.");
            }
        },
    });

    return {
        createCategoryMutation,
        updateCategoryMutation,
        deleteCategoryMutation
    }
}