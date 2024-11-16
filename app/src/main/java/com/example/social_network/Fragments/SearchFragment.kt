package com.example.social_network.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.social_network.UsersAdapter
import com.example.social_network.data.network.RetrofitInstance.getApiService
import com.example.social_network.databinding.FragmentSearchBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        // Инициализация RecyclerView и адаптера
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersAdapter()  // Не передаем контекст
        recyclerView.adapter = adapter

        // Установка слушателя на кнопку поиска
        binding.buttonSearch.setOnClickListener {
            // Логируем нажатие
            Log.d("BUTTON_CLICK", "Button clicked!")

            val query = binding.editTextSearch.text.toString()
            searchUsers(query)
        }

        return binding.root
    }


    // Метод для поиска пользователей по имени
    private fun searchUsers(query: String) {
        if (query.isEmpty()) {
            Toast.makeText(requireContext(), "Введите имя для поиска", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch(Dispatchers.Main) {
            try {
                val apiService = getApiService() // Получаем экземпляр API сервиса
                val response = withContext(Dispatchers.IO) {
                    apiService.searchUsersByName(query) // Получаем список пользователей
                }
                Log.d("IWAAAAANTTHIS SHIT", response.toString())
                // Передаем полученные данные в адаптер для отображения
                response.body()?.let { adapter.submitList(it) }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Ошибка поиска: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
