import { useMutation, useQueryClient } from "@tanstack/react-query"
import { ProductFormData } from "@/shared/types/types";
import { productApi } from "@/shared/api/api";
import { toast } from "react-toastify";
import { AxiosError } from "axios";
import { IPagedResponse } from "@/shared/types/IPagedResponse";

export const useProductMutations = () => {
    const queryClient = useQueryClient();

    const createProductMutation = useMutation({
        mutationFn: (data: FormData) => productApi.createProduct(data),
        onSuccess: (newProduct) => {
            queryClient.setQueryData<IPagedResponse<ProductFormData[]>>(
                ["products"],
                (oldData) => {
                    if (oldData) {
                        return {
                            content: [...oldData.content, newProduct],
                            page: oldData.page,
                        };
                    }

                    return {
                        content: [newProduct],
                        page: { size: 1, number: 0, totalElements: 1, totalPages: 1 },
                    };
                }
            );
            toast.success("Produto cadastrado com sucesso!");
        },
        onError: (error) => {
            if (error instanceof AxiosError) {
                toast.error(error.response?.data?.error || "Erro na requisição Post.");
            }
        },
    });

    const deleteProductMutation = useMutation({
        mutationFn: (id: number) => productApi.deleteObject(id),

        onSuccess: (_, id) => {
            queryClient.setQueryData<IPagedResponse<ProductFormData[]>>(
                ["products"],
                (oldData) => {
                    if (!oldData) return oldData;

                    return {
                        ...oldData,
                        content: oldData.content.filter(
                            (product) => product.id !== id
                        ),
                    };
                }
            );

            toast.success("Produto excluído com sucesso!");
        },

        onError: (error) => {
            if (error instanceof AxiosError) {
                toast.error(
                    error.response?.data?.error || "Erro ao excluir produto."
                );
            }
        },
    });

    const updateProductMutation = useMutation({
        mutationFn: (formData: FormData) =>
            productApi.updateProduct(formData),

        onSuccess: (updatedProduct) => {
            queryClient.setQueryData<IPagedResponse<ProductFormData[]>>(
                ["products"],
                (oldData) => {
                    if (!oldData) return oldData;

                    return {
                        ...oldData,
                        content: oldData.content.map((product) =>
                            product.id === updatedProduct.id
                                ? updatedProduct
                                : product
                        ),
                    };
                }
            );

            toast.success("Produto atualizado com sucesso!");
        },

        onError: (error) => {
            if (error instanceof AxiosError) {
                toast.error(
                    error.response?.data?.error || "Erro ao atualizar produto."
                );
            }
        },
    });

    return {
        createProductMutation,
        deleteProductMutation,
        updateProductMutation
    }
}