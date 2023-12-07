package com.example.adway_project

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DetailFragment : Fragment() {

    private var imageResId: Int = 0
    private var text: String = ""

    companion object {
        fun newInstance(imageResId: Int, text: String) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt("imageResId", imageResId)
                putString("text", text)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageResId = it.getInt("imageResId")
            text = it.getString("text", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        // 이미지와 텍스트 뷰를 찾고, 변수에 저장된 데이터로 설정
        val imageView = view.findViewById<ImageView>(R.id.detail_image)
        val textView = view.findViewById<TextView>(R.id.detail_text)
        val detail_text_1 = view.findViewById<TextView>(R.id.detail_text_1)
        val detail_text_2 = view.findViewById<TextView>(R.id.detail_text_2)
        val detail_text_3 = view.findViewById<TextView>(R.id.detail_text_3)

        val detail_text_price = view.findViewById<TextView>(R.id.detail_text_price)
        val editTextMonth = view.findViewById<EditText>(R.id.editTextMonth)
        val textViewAmount = view.findViewById<TextView>(R.id.textViewAmount)
        val buttonCalculate = view.findViewById<Button>(R.id.buttonCalculate)

        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        val reserveButton = view.findViewById<Button>(R.id.reserveButton)

        imageView.setImageResource(imageResId)
        textView.text = text

        buttonCalculate.setOnClickListener {
            try {
                // editTextMonth와 detail_text_price에서 값을 가져옴
                val month = editTextMonth.text.toString().toInt()
                val price = detail_text_price.text.toString().toInt()

                // 두 값을 곱함
                val totalAmount = month * price

                // 결과를 textViewAmount에 표시
                textViewAmount.text = totalAmount.toString()
            } catch (e: NumberFormatException) {
                Toast.makeText(context, "원하는 개월 수를 숫자로 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        reserveButton.setOnClickListener {

            val selectedDate = Calendar.getInstance().apply {
                timeInMillis = calendarView.date
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }

            val today = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }

            if (selectedDate.before(today)) {
                // 과거의 날짜를 선택한 경우
                Toast.makeText(context, "과거의 날짜는 선택할 수 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    // 미래의 날짜를 선택한 경우
                    val monthToAdd = editTextMonth.text.toString().toInt()

                    val originalDay = selectedDate.get(Calendar.DAY_OF_MONTH)
                    selectedDate.add(Calendar.MONTH, monthToAdd)

                    // 새 달에서 가능한 일수를 체크하고 조정
                    val maxDayInNewMonth = selectedDate.getActualMaximum(Calendar.DAY_OF_MONTH)
                    if (originalDay > maxDayInNewMonth) {
                        // 선택한 일이 새 달의 최대 일수보다 크면 해당 달의 마지막 일로 설정
                        selectedDate.set(Calendar.DAY_OF_MONTH, maxDayInNewMonth)
                    } else {
                        // 가능하면 같은 일로 설정
                        selectedDate.set(Calendar.DAY_OF_MONTH, originalDay)
                    }

                    // 예약 종료 날짜 포맷팅
                    val endDateFormatted = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault()).format(selectedDate.time)

                    // AlertDialog로 예약 날짜 확인
                    AlertDialog.Builder(context)
                        .setTitle("예약 확인")
                        .setMessage("선택하신 날짜부터 $endDateFormatted 까지 예약하시겠습니까?")
                        .setPositiveButton("확인") { dialog, which ->
                            Toast.makeText(context, "예약이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        }
                        .setNegativeButton("취소") { dialog, which ->
                            Toast.makeText(context, "예약이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                        }
                        .show()
                } catch (e: NumberFormatException) {
                    Toast.makeText(context, "원하는 개월 수를 숫자로 입력해 주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        when (text){

            ///////  2호선  ///////
            "강남역" -> {
                detail_text_1.text = "[2호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_2))
                detail_text_2.text = "약 15,000명"
                detail_text_3.text = "승강장 파노라마 미디어 플랫폼"
                detail_text_price.text = "3000"
            }
            "왕십리역" -> {
                detail_text_1.text = "[2호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_2))
                detail_text_2.text = "약 3,000명"
                detail_text_3.text = "조명와이드컬러"
                detail_text_price.text = "400"
            }
            "건대입구역" -> {
                detail_text_1.text = "[2호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_2))
                detail_text_2.text = "약 8,000명"
                detail_text_3.text = "조명와이드컬러"
                detail_text_price.text = "400"
            }
            "잠실역" -> {
                detail_text_1.text = "[2호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_2))
                detail_text_2.text = "약 15,000명"
                detail_text_3.text = "조명와이드컬러"
                detail_text_price.text = "400"
            }
            "사당역" -> {
                detail_text_1.text = "[2호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_2))
                detail_text_2.text = "약 9,000명"
                detail_text_3.text = "스크린도어"
                detail_text_price.text = "1500"
            }
            "종합운동장역" -> {
                detail_text_1.text = "[2호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_2))
                detail_text_2.text = "약 3,000명"
                detail_text_3.text = "벽면, 기둥래핑"
                detail_text_price.text = "200"
            }
            "신도림역" -> {
                detail_text_1.text = "[2호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_2))
                detail_text_2.text = "약 9,000명"
                detail_text_3.text = "스크린도어"
                detail_text_price.text = "500"
            }
            "합정역" -> {
                detail_text_1.text = "[2호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_2))
                detail_text_2.text = "약 7.000명"
                detail_text_3.text = "포스터"
                detail_text_price.text = "30"
            }
            "홍대입구역" -> {
                detail_text_1.text = "[2호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_2))
                detail_text_2.text = "약 14,000명"
                detail_text_3.text = "디지털포스터"
                detail_text_price.text = "400"
            }

            ///////  4호선  ///////
            "노원역" -> {
                detail_text_1.text = "[4호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_4))
                detail_text_2.text = "약 2,000명"
                detail_text_3.text = "와이드칼라"
                detail_text_price.text = "600"
            }
            "혜화역" -> {
                detail_text_1.text = "[4호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_4))
                detail_text_2.text = "약 3,500명"
                detail_text_3.text = "와이드칼라"
                detail_text_price.text = "600"
            }
            "명동역" -> {
                detail_text_1.text = "[4호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_4))
                detail_text_2.text = "약 3,300명"
                detail_text_3.text = "와이드칼라"
                detail_text_price.text = "600"
            }
            "서울역" -> {
                detail_text_1.text = "[4호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_4))
                detail_text_2.text = "약 1,500명"
                detail_text_3.text = "스크린도어"
                detail_text_price.text = "300"
            }
            "충무로역" -> {
                detail_text_1.text = "[4호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_4))
                detail_text_2.text = "약 2,700명"
                detail_text_3.text = "스크린도어"
                detail_text_price.text = "300"
            }
            "동대문역" -> {
                detail_text_1.text = "[4호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_4))
                detail_text_2.text = "약 2,000명"
                detail_text_3.text = "와이드칼라"
                detail_text_price.text = "450"
            }
            "미아사거리역" -> {
                detail_text_1.text = "[4호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_4))
                detail_text_2.text = "약 2,400명"
                detail_text_3.text = "스크린도어"
                detail_text_price.text = "300"
            }
            "성신여대입구역" -> {
                detail_text_1.text = "[4호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_4))
                detail_text_2.text = "약 2,000명"
                detail_text_3.text = "디지털포스터"
                detail_text_price.text = "200"
            }
            "총신대입구역" -> {
                detail_text_1.text = "[4호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_4))
                detail_text_2.text = "약 1,700명"
                detail_text_3.text = "스크린도어"
                detail_text_price.text = "300"
            }

            ///////  5호선  ///////
            "여의도역" -> {
                detail_text_1.text = "[5호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_5))
                detail_text_2.text = "약 7,000명"
                detail_text_3.text = "와이드칼라"
                detail_text_price.text = "300"
            }
            "동대문역사문화공원역" -> {
                detail_text_1.text = "[5호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_5))
                detail_text_2.text = "약 1,000명"
                detail_text_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f);
                detail_text_3.text = "스크린도어"
                detail_text_price.text = "800"
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f);
            }
            "천호역" -> {
                detail_text_1.text = "[5호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_5))
                detail_text_2.text = "약 3,600명"
                detail_text_3.text = "와이드칼라"
                detail_text_price.text = "300"
            }
            "종로3가역" -> {
                detail_text_1.text = "[5호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_5))
                detail_text_2.text = "약 3,200명"
                detail_text_3.text = "스크린도어"
                detail_text_price.text = "600"
            }
            "마포역" -> {
                detail_text_1.text = "[5호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_5))
                detail_text_2.text = "약 3,300명"
                detail_text_3.text = "지하철 편성 광고(독점)"
                detail_text_price.text = "500"
            }
            "행당역" -> {
                detail_text_1.text = "[5호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_5))
                detail_text_2.text = "약 1,700명"
                detail_text_3.text = "지하철 편성 광고(독점)"
                detail_text_price.text = "500"
            }
            "아차산역" -> {
                detail_text_1.text = "[5호선]"
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.subway_line_5))
                detail_text_2.text = "약 2,800명"
                detail_text_3.text = "포스터 광고"
                detail_text_price.text = "40"
            }

            else -> {
                // 없음
            }

        }

        return view
    }
}